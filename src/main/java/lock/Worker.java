package lock;

import lock.style.*;

import java.util.HashMap;
import java.util.Map;

public class Worker implements Runnable {
    static Map<Class, LockStyle> styleMap = new HashMap<>();

    static {
        styleMap.put(ReentrantRawLock.class, new ReentrantRawLock());
        styleMap.put(ReentrantTryLock.class, new ReentrantTryLock());
        styleMap.put(SynchronizedLock.class, new SynchronizedLock());
        styleMap.put(ReadWriteRawLock.class, new ReadWriteRawLock());
        styleMap.put(RedisDistributeLock.class, new RedisDistributeLock());
    }

    protected Resource resource;

    public Worker() {
    }

    public Worker(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        LockStyle lockStyle = styleMap.get(resource.getLockStyle());
        if (lockStyle != null) {
            lockStyle.lockAndRun(resource);
        } else {
            resource.apply();
        }

    }

    public String currentThreadName() {
        return Thread.currentThread().getName();
    }
}
