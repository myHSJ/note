package lock.style;

import lock.Resource;
import lock.Worker;
import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RedisDistributeLock extends Worker implements LockStyle {
    static JedisPool jedisPool;

    //脚本获取锁
    /*超时问题 ：一个线程获取锁之后阻塞了 锁超时 导致另外一个线程再次获得锁 这时有两个线程同时获取到了锁*/
    //解锁的操作时，要比对val 避免将其他线程的锁给释放
    //加锁的时间要足够长
    public final static String LOCK_SCRIPT = "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then redis.call('pexpire', KEYS[1], ARGV[2]) return 1 else return 0 end ";
    public final static String UNLOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    static {
        jedisPool = new JedisPool("localhost", 6379);
    }


    /**
     * Java redis lock
     */
    public void lockAndRun(Resource resource) {
        //普通锁
        //normalLockMethod();
        //lua锁
        luaLockMethod();


    }

    public void normalLockMethod() {
        String key = resource.getKey();
        String id = null;
        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            id = acquireLockWithTimeout(conn, key, 5, 10);
            if (id == null) {
                System.out.println(currentThreadName() + "获取锁失败");
                return;
            }
            resource.apply();
        } finally {
            releaseLock(conn, key, id);
            conn.close();
        }
    }

    public void luaLockMethod() {
        String key = resource.getKey();
        String id = null;
        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            id = luaRedisLock(conn, key, 15, 5);
            if (id == null) {
                System.out.println(currentThreadName() + "获取锁失败");
                return;
            }
            resource.apply();
        } finally {
            luaRedisUnLock(conn, key, id);
            conn.close();
        }
    }


    /*lua加锁*/
    public String luaRedisLock(Jedis jedis, String key, long acquireTimeOut, long lockTimeOut) {


        String id = UUID.randomUUID().toString();
        long end = System.currentTimeMillis() + (acquireTimeOut * 1000);

        while (System.currentTimeMillis() < end) {
            Object obj = jedis.eval(LOCK_SCRIPT, Arrays.asList(key), Arrays.asList(id, String.valueOf(lockTimeOut * 1000)));
            if (obj.toString().equals("1")) {
                return id;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.interrupted();
            }
        }

        return null;
    }

    /*lua解锁*/
    public static boolean luaRedisUnLock(Jedis jedis, String key, String requestId) {
        if (requestId == null) return false;
        //比对id，避免其他线程的锁被释放
        Object obj = jedis.eval(UNLOCK_SCRIPT, Arrays.asList(key), Arrays.asList(requestId));
        return obj.toString().equals("1");
    }


    public boolean releaseLock(Jedis conn, String lockName, String identifier) {
        if (identifier == null) return false;
        lockName = "lock:" + lockName;
        while (true) {
            conn.watch(lockName);
            if (identifier.equals(conn.get(lockName))) {
                Transaction trans = conn.multi();
                trans.del(lockName);
                List<Object> result = trans.exec();
                // null response indicates that the transaction was aborted due
                // to the watched key changing.
                if (result == null) {
                    continue;
                }
                System.out.println(currentThreadName() + "释放锁");
                return true;
            }

            conn.unwatch();
            break;
        }

        return false;
    }


    public String acquireLockWithTimeout(Jedis conn, String lockName, int acquireTimeout, int lockTimeout) {
        String id = UUID.randomUUID().toString();
        lockName = "lock:" + lockName;
        long end = System.currentTimeMillis() + (acquireTimeout * 1000);
        while (System.currentTimeMillis() < end) {
            if (conn.setnx(lockName, id) >= 1) {
                conn.expire(lockName, lockTimeout);
                System.out.println(currentThreadName() + "获取到锁");
                return id;
            } else if (conn.ttl(lockName) <= 0) {
                conn.expire(lockName, lockTimeout);
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
                Thread.interrupted();
            }
        }

        return null;
    }
}
