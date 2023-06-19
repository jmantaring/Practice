package threading.multi;

import java.util.Random;

public class MetricsUseCase {

    public static void main(String[] args) {
        Metrics metrics = new Metrics();

        BusinessLogic businessLogicThread1 = new BusinessLogic(metrics);
        BusinessLogic businessLogicThread2 = new BusinessLogic(metrics);

        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

        businessLogicThread1.start();
        businessLogicThread2.start();
        metricsPrinter.start();
    }

    public static class MetricsPrinter extends Thread {
        private Metrics metrics;

        public MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}

                // 2nd thread that access the average variable
                double currentAverage = metrics.getAverage();

                System.err.println("Current Average is " + currentAverage);
            }
        }
    }

    public static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {

            while(true) {
                long start = System.currentTimeMillis();

                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {}

                long end = System.currentTimeMillis();

                /**
                 * first thread that access the addSample
                 * which also access the average variable since
                 *  addSample calculates the average
                 */
                metrics.addSample(end - start);
            }
        }
    }

    public static class Metrics {
        private long count = 0;
        private volatile double average = 0.0;

        /**
         * This method is not an atomic operation (because it has multiple steps)
         * We know that it will be accessed by multiple threads so..
         * So we have to protect the method execution by placing synchronizing the method OR
         * Place the implementation to a synchronized block.
         * But we will chose to synchronize the method since our current use case is we need the whole implementation to be synchronized
         */
        public synchronized void addSample(long sample) {
            double currentSum = average + count;
            count++;
            average = (currentSum + sample) / count;
        }

        /**
         * We know that read/write on primitive types are atomic operation except for long and double
         * We also know that this will be accessed by multiple threads so..
         * Since this is a getter, we will use a volatile keyword on the average,
         *  so that everything will be read/wrote to the main shared memory NOT ON THE CACHE OF EACH THREAD
         */
        public double getAverage() {
            return average;
        }
    }

}
