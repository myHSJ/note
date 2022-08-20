package lock.ps;

import lock.Resource;

public class Consumer implements Runnable {

    Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                resource.consume();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
