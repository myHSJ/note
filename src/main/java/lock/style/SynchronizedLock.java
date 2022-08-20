package lock.style;

import lock.Resource;
import lock.Worker;

public class SynchronizedLock extends Worker implements LockStyle {

    @Override
    public void lockAndRun(Resource resource) {
        //synchronized (this) 是在所在类的对象加锁
        //synchronized (Worker.class) 是给指定的类加锁
        //普通方法synchronized加锁的对象this，如果是静态方法则加锁的对象是当前类
        //Object obj = new Object();synchronized(obj)，给指定的对象加锁
        synchronized (SynchronizedLock.class) {
            resource.apply();
        }
    }


}
