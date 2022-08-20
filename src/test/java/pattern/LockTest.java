package pattern;

import lock.CacheResource;
import lock.CountResource;
import lock.Resource;
import lock.Worker;
import lock.ps.BlockQueueResource;
import lock.ps.Consumer;
import lock.ps.Producer;
import lock.style.ReadWriteRawLock;
import lock.style.SynchronizedLock;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class LockTest {


    //测试多线程累计器，多种加锁方式
    @Test
    public void testMultipleLockStyle() throws Exception {
        //counter
        List<Thread> threadList = new ArrayList<>();
        Resource resource = new CountResource();
        resource.setLockStyle(SynchronizedLock.class);
        runWithThread(resource, threadList);
    }

    //测试读写锁
    @Test
    public void testReadWriteLock() throws Exception {
        //cache
        getCache();
    }


    @Test
    public void testConsumerProducer() throws Exception {
        consumerAndProducer();
    }


    public static void counter() {
        List<Thread> threadList = new ArrayList<>();
        Resource resource = new CountResource();
        runWithThread(resource, threadList);
    }

    public static void consumerAndProducer() {
        try {
            List<Thread> threadList = new ArrayList<>();
            BlockQueueResource resource = new BlockQueueResource();
            resource.setData(new ArrayBlockingQueue(3));
            runWithConsumer(resource, threadList);
            runWithProducer(resource, threadList);

            for (Thread thread : threadList) {
                thread.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getCache() {
        try {
            List<Thread> threadList = new ArrayList<>();
            //所有的线程都操作同一个MAP
            Map<String, String> commonMap = new HashMap<>();
            Arrays.asList("A", "B", "D").forEach(it -> {
                Resource resource = new CacheResource();
                resource.setKey(it);
                resource.setData(commonMap);
                resource.setLockStyle(ReadWriteRawLock.class);
                runWithThread(resource, threadList);
            });

            for (Thread thread : threadList) {
                thread.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void runWithThread(Resource resource, List<Thread> threadList) {
        try {
            for (int i = 0; i < 12; i++) {
                Worker worker = new Worker(resource);
                Thread thread = new Thread(worker);
                thread.start();
                threadList.add(thread);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void runWithConsumer(Resource resource, List<Thread> threadList) {
        try {
            for (int i = 0; i < 4; i++) {
                Consumer worker = new Consumer(resource);
                Thread thread = new Thread(worker);
                thread.start();
                threadList.add(thread);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void runWithProducer(Resource resource, List<Thread> threadList) {
        try {
            for (int i = 0; i < 4; i++) {
                Producer worker = new Producer(resource);
                Thread thread = new Thread(worker);
                thread.start();
                threadList.add(thread);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
