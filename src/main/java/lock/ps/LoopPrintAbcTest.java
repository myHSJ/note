package lock.ps;

public class LoopPrintAbcTest {

    static volatile int state = 0;
    final static Object obj = new Object();

    public static class MyThread extends Thread {
        private int which;

        public MyThread(int which) {
            this.which = which;
        }

        @Override
        public void run() {
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    //state是递增的，所以其余的两个线程都会在下面的wait方法阻塞
                    while (state % 3 != which) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(convert(which));
                    state++;
                    obj.notifyAll();
                }
            }
        }

        public String convert(int which) {
            return (char) ('A' + which) + "";
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread(0);
        MyThread t2 = new MyThread(1);
        MyThread t3 = new MyThread(2);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
