package threading.joining;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThreadJoinWithFactorials {

    public static void main(String[] args) {
        final List<Long> inputNumbers = Arrays.asList(0L, 3435L, 35435L, 2324L, 4656L, 23L, 2435L, 5566L);

        final List<FactorialThread> factorialThreads = inputNumbers.stream()
                .map(FactorialThread::new)
                .collect(Collectors.toList());

        factorialThreads.forEach(FactorialThread::start);

        // join only return when the thread is terminated.
//        factorialThreads.forEach(factorialThread -> {
//            try {
//                factorialThread.join(2000);
//            } catch (InterruptedException e) {
//                System.err.println("Unexpected interruption from " + factorialThread.getName());
//            }
//        });

        /**
         * This will cause a raise condition as there are some large number that will take a while to compute factorial
         * and the main thread will be done by then before the other threads are done with there computation.
         *
         * Solution would be uncomment the join above
         */
        printFactorialThreadResultsWithoutJoining(factorialThreads);
    }

    private static void printFactorialThreadResultsWithoutJoining(final List<FactorialThread> factorialThreads) {
        factorialThreads.forEach(thread -> {
            if (thread.isFinished()) {
                System.err.println(String.format("Factorial result for input %d is %d", thread.inputNumber, thread.getResult()));
            } else {
                System.err.println(String.format("The calculation for %d is still in progress", thread.inputNumber));
            }
        });
    }

    private static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ONE;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        private BigInteger factorial(long number) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = number; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }

            return tempResult;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }

}
