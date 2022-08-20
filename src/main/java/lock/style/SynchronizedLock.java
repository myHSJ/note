package lock.style;

import lock.Resource;
import lock.Worker;

public class SynchronizedLock extends Worker implements LockStyle {

    @Override
    public void lockAndRun(Resource resource) {
        //类锁：synchronized (this) 等同于  synchronized (Worker.class)
        //对象锁：synchronized(resource)，其中resource为上面的传进来的资源实体
        synchronized (SynchronizedLock.class) {
            resource.apply();
        }
    }


}
