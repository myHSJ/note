package lock.style;

import lock.Resource;
import lock.Worker;

//读锁和写锁
public class ReadWriteRawLock extends Worker implements LockStyle {

    /**
     * Java reentrantLock
     */
    public void lockAndRun(Resource resource) {
        try {
            resource.reentrantLock.lock();
            resource.apply(resource.getKey());
        } finally {
            resource.reentrantLock.unlock();
        }
    }


}
