package lock.ps;

import lock.Resource;

public class Producer implements Runnable {

    Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
                resource.produce();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
