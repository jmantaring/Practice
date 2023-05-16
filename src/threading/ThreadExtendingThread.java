package threading;

public class ThreadExtendingThread {

    public static void main(String[] args) {
        Thread thread = new NewThread();

        thread.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.err.println("Hello from " + this.getName());
        }
    }

}
