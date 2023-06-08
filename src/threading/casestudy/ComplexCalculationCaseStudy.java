package threading.casestudy;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class ComplexCalculationCaseStudy {

    public static void main(String[] args) {

        final ComplexCalculationCaseStudy calculator = new ComplexCalculationCaseStudy();
        final BigInteger result = calculator.calculateResult(
                new BigInteger("5"),
                new BigInteger("2"),
                new BigInteger("5"),
                new BigInteger("2")
        );

        System.err.println(result);
    }

    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        List<PowerCalculatingThread> threads = Arrays.asList(
                new PowerCalculatingThread(base1, power1),
                new PowerCalculatingThread(base2, power2)
        );

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {

            }
        });

        result = threads.stream()
                .map(PowerCalculatingThread::getResult)
                .reduce(BigInteger.ZERO, (subtotal, element) -> subtotal.add(element));

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */
            this.result = Math.calculatePower(base, power);
        }

        public BigInteger getResult() { return result; }
    }

    public static class Math {
        public static BigInteger calculatePower(final BigInteger base, final BigInteger power) {

            BigInteger result = base;

            for (BigInteger index = power; index.compareTo(BigInteger.ONE) > 0; index = index.subtract(BigInteger.ONE)) {
                result = result.multiply(base);
            }

            return result;
        }
    }

}
