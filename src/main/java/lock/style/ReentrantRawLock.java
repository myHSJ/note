package lock.style;

import lock.Resource;
import lock.Worker;

public class ReentrantRawLock extends Worker implements LockStyle {

    /**
     * Java reentrantLock
     */
    public void lockAndRun(Resource resource) {
        try {
            resource.reentrantLock.lock();
            resource.apply();
        } finally {
            resource.reentrantLock.unlock();
        }
    }


}
