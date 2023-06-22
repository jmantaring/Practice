package threading.stopping;

import java.math.BigInteger;

public class ThreadWithLongComputation {

    public static void main(String[] args) {
        final BigInteger base = new BigInteger("100000");
        final BigInteger power = new BigInteger("10000000000");

        final Thread threadWithInterruptChecking = new Thread(
                new LongComputationTask(base, power),
                "ThreadWithInterruptChecking"
        );

        threadWithInterruptChecking.start();
        // Try to interrupt the threadWithInterruptChecking. Handling can be found on LongComputationTask.pow
        threadWithInterruptChecking.interrupt();

        final Thread daemonThread = new Thread(
                new LongComputationTaskWithNoInterruptChecking(base, power),
                "DaemonThread"
        );

        // Setting thread to daemon so interrupt would work. Try commenting the setDaemon and check behavior
        daemonThread.setDaemon(true);
        daemonThread.start();
        daemonThread.interrupt();
    }

    private static class LongComputationTask implements Runnable {

        private final BigInteger base;
        private final BigInteger power;

        private LongComputationTask(final BigInteger base, final BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.err.println(
                    String.format("Thread %s: %d^%d=%d",
                            Thread.currentThread().getName(),
                            this.base,
                            this.power,
                            this.pow(this.base, this.power))
            );
        }

        private BigInteger pow(final BigInteger base, final BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                // If we don't place this, even we interrupt the thread, the thread will not stop
                if (Thread.currentThread().isInterrupted()) {
                    System.err.println("Prematurely interrupted computation");
                    return result;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }

    // No interruption checking
    private static class LongComputationTaskWithNoInterruptChecking implements Runnable {

        private final BigInteger base;
        private final BigInteger power;

        private LongComputationTaskWithNoInterruptChecking(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.err.println(
                    String.format("Thread %s: %d^%d=%d",
                            Thread.currentThread().getName(),
                            this.base,
                            this.power,
                            this.pow(this.base, this.power))
            );
        }

        // No interruption checking
        private BigInteger pow(final BigInteger base, final BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
                System.err.println(
                        String.format("%s computing result %d", Thread.currentThread().getName(), result)
                );
            }

            return result;
        }
    }
}
