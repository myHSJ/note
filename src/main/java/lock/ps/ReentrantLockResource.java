package lock.ps;

import lock.Resource;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockResource extends Resource<LinkedList<String>> {

    private int limit = 10;
    public final ReentrantLock reentrantLock = new ReentrantLock();

    //声明condition是为了当满足条件时，将对应的线程阻塞
    Condition full = reentrantLock.newCondition();
    Condition empty = reentrantLock.newCondition();

    public void produce() {
        reentrantLock.lock();
        try {
            int idx = counter.get();
            while (data.size() >= limit) {
                try {
                    System.out.println(currentThreadName() + "第 " + idx + "次添加元素，List已满，等待添加元素");
                    full.await();//
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(currentThreadName() + "第 " + idx + "次添加元素");
            counter.set(idx + 1);
            data.add("123");
            empty.signalAll();
        } finally {
            reentrantLock.unlock();
        }

    }

    public void consume() {
        reentrantLock.lock();
        try {
            int idx = counter.get();
            while (data.size() == 0) {
                try {
                    System.out.println(currentThreadName() + "第 " + idx + "次消费元素，List已空，等待消费元素");
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(currentThreadName()  + "第 " + idx + "次消费元素");
            data.removeFirst();
            counter.set(idx + 1);
            full.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }
}
