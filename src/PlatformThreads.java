public class PlatformThreads {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> System.out.println("Inside Thread: "+Thread.currentThread().getName());

        Thread platformThread = new Thread(runnable);

        platformThread.start();
        platformThread.join();
    }

}
