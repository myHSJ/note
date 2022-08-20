package lock.style;

import lock.Resource;
import lock.Worker;

import java.util.concurrent.TimeUnit;

public class ReentrantTryLock extends Worker implements LockStyle {
    /**
     * Java tryLock
     */
    @Override
    public void lockAndRun(Resource reqResource) {
        try {
            boolean flag = resource.reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS);
            while (!flag) {
                System.out.println(currentThreadName() + "获取锁失败");
                flag = resource.reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS);
            }
            System.out.println(currentThreadName() + "获取锁成功");
            //临界区代码实现
            resource.apply();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println(currentThreadName() + "释放锁");
            resource.reentrantLock.unlock();
        }
    }
}
