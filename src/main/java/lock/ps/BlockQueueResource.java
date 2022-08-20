package lock.ps;

import lock.Resource;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class BlockQueueResource extends Resource<BlockingQueue> {

    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    private int limit = 10;

    public void produce() {
        try {
            System.out.println(currentThreadName() + "第 " + threadLocal.get() + "次添加元素开始");
            data.put("123");
            System.out.println(currentThreadName() + "第 " + threadLocal.get() + "次添加元素成功");
            threadLocal.set(threadLocal.get() + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        try {
            data.take();
            System.out.println(currentThreadName() + "第 " + threadLocal.get() + "次消费元素");
            threadLocal.set(threadLocal.get() + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
