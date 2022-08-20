package lock;

import lock.ps.*;
import lock.style.ReadWriteRawLock;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class JavaLock {

    public static void main(String[] args) throws Exception {

        //counter
        //counter();

        //cache
        //getCache();

        //
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
            //resource.setData(new LinkedList<>());
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
