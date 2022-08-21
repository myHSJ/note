package lock.style;

import lock.CacheResource;
import lock.Resource;
import lock.Worker;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//读锁和写锁
public class ReadWriteRawLock extends Worker implements LockStyle {

    //读写锁
    public static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public final Lock readLock = readWriteLock.readLock();
    public final Lock writeLock = readWriteLock.writeLock();

    /**
     * Java read and write lock
     */
    public void lockAndRun(Resource resource) {

        String res = null, key = resource.getKey();
        readLock.lock();
        try {
            ((CacheResource) resource).readData(resource.getKey());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            readLock.unlock();
        }

        if (Objects.nonNull(res)) {
            System.out.println(currentThreadName() + "缓存中 key " + key + "的值为：" + res);
        }

        writeLock.lock();
        try {
            ((CacheResource) resource).writeData(resource.getKey());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }


}
