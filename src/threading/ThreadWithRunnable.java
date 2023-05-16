package threading;

public class ThreadWithRunnable {

    public static void main(String[] args) throws InterruptedException {
        final Thread thread = createBasicThreadWithName();
        final Thread misbehavingThread = createBasicThreadWithExceptionHandler();

        System.err.println("We are in thread: " + Thread.currentThread().getName() + " before starting the new thread");
        thread.start(); // Instruct the JVM to create a new thread and pass it to the OS
        misbehavingThread.start();
        System.err.println("We are in thread: " + Thread.currentThread().getName() + " after starting the new thread");

//        Thread.sleep(10000); // Instructs the OS to not schedule the current thread until the given param time passes.
    }

    public static Thread createBasicThreadWithName() {
        final Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                System.err.println("We are now in thread " + Thread.currentThread().getName());
                System.err.println("Current thread priority is " + Thread.currentThread().getPriority());
            }
        });

        thread.setName("New Worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY - 1); // 1 - 10

        return thread;
    }

    public static Thread createBasicThreadWithExceptionHandler() {
        final Thread thread = new Thread(() -> {
            System.err.println("We are now in thread " + Thread.currentThread().getName());
            System.err.println("Current thread priority is " + Thread.currentThread().getPriority());
            throw new RuntimeException("Intentional Exception");
        });

        thread.setName("Misbehaving thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.println("A critical error happend in thread " + t.getName()
                        + " the error is " + e.getMessage());
            }
        });

        return thread;
    }

}
