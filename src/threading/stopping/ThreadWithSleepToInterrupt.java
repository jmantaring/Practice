package threading.stopping;

public class ThreadWithSleepToInterrupt {

    public static void main(String[] args) {
        Thread thread = new Thread(new BlockingTask());
        thread.start();
        thread.interrupt();
    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                try { // If this code is outside the loop it can terminate the app
                    Thread.sleep(500000);
                } catch (InterruptedException e) {
                    System.err.println("Exiting blocking thread.");
                    // Always try to handle the InterruptedException exception block as if not, it will keep the app running since where inside the while true loop
                    return;
                }
            }
        }
    }

}
