import java.util.ArrayList;
import java.util.List;

public class VirtualThread {

    private static Integer NUMBER_OF_VIRTUAL_THREADS = 100;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> System.out.println("Inside Thread: "+Thread.currentThread());

        List<Thread> virtualThreads = new ArrayList<>();

        for (int i=0; i<NUMBER_OF_VIRTUAL_THREADS; i++) {
            Thread virtualThread = Thread.ofVirtual().unstarted(new BlockingIO());
            virtualThreads.add(virtualThread);
        }

        for (Thread vt: virtualThreads) {
            vt.start();
        }
        for (Thread vt: virtualThreads) {
            vt.join();
        }
    }

    private static class BlockingIO implements Runnable {
        @Override
        public void run() {
            System.out.println("Inside Thread: "+Thread.currentThread()+" before blocking call");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Inside Thread: "+Thread.currentThread()+" after blocking call");
        }
    }

}
