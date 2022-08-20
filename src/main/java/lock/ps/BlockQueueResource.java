package lock.ps;

import lock.Resource;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class BlockQueueResource extends Resource<BlockingQueue> {

    private int limit = 10;

    public void produce() {
        try {
            System.out.println(currentThreadName() + "第 " + counter.get() + "次添加元素开始");
            data.put("123");
            System.out.println(currentThreadName() + "第 " + counter.get() + "次添加元素成功");
            counter.set(counter.get() + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        try {
            data.take();
            System.out.println(currentThreadName() + "第 " + counter.get() + "次消费元素");
            counter.set(counter.get() + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
