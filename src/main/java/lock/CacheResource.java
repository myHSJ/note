package lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CacheResource extends Resource<Map<String, String>> {

    //模拟数据库的数据
    static Map<String, String> db = new HashMap<>();

    static {
        db.put("A", "1");
        db.put("B", "2");
        db.put("C", "3");
        db.put("D", "4");
    }

    public CacheResource() {

    }


    public String readData(String key) {
        String res = null;
        return data.get(key);
    }

    public void writeData(String key) {
        String res = null;
        res = db.get(key);
        if (!data.containsKey(key)) {
            System.out.println(currentThreadName() + "设置了缓存" + key);
            data.put(key, res);
        } else {
            System.out.println(currentThreadName() + "数据库中 key " + key + "的值为：" + res);
        }
    }

    //读写锁
   /* public String apply(String key) {
        String res = null;
        readLock.lock();
        try {
            res = data.get(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            readLock.unlock();
        }

        if (Objects.nonNull(res)) {
            System.out.println(currentThreadName() + "缓存中 key " + key + "的值为：" + res);
            return res;
        }

        writeLock.lock();
        try {
            res = db.get(key);
            if (!data.containsKey(key)) {
                System.out.println(currentThreadName() + "设置了缓存" + key);
                data.put(key, res);
            } else {
                System.out.println(currentThreadName() + "数据库中 key " + key + "的值为：" + res);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writeLock.unlock();
        }

        return res;
    }*/
}
