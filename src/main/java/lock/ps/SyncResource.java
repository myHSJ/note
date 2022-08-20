package lock.ps;

import lock.Resource;

import java.util.LinkedList;

public class SyncResource extends Resource<LinkedList<String>> {

    private int limit = 10;

    public void produce() {
        synchronized (data) {
            while (data.size() >= limit) {
                try {
                    System.out.println(currentThreadName() + "List已满，等待添加元素");
                    data.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(currentThreadName() + "添加元素");
            data.add("123");
            data.notifyAll();
        }
    }

    public void consume() {
        synchronized (data) {
            while (data.size() == 0) {
                try {
                    System.out.println(currentThreadName() + "List已空，等待消费元素");
                    data.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(currentThreadName() + "消费元素");
            data.remove();
            data.notifyAll();
        }
    }
}
