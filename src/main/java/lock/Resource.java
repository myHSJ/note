
package lock;

import lombok.Data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Data
public abstract class Resource<U> {

    public static ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 1);

    protected U data;

    //显示锁
    public final ReentrantLock reentrantLock = new ReentrantLock();

    private Class<?> lockStyle;

    private String key;

    //core logic
    public void apply() {
        System.out.println("没有实现");
    }

    //core logic
    public String apply(String key) {
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String currentThreadName() {
        return Thread.currentThread().getName();
    }

    public Class<?> getLockStyle() {
        return lockStyle;
    }

    public void setLockStyle(Class<?> lockStyle) {
        this.lockStyle = lockStyle;
    }

    public void produce() {
    }

    public void consume() {
    }


}