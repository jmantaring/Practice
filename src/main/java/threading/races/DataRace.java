package threading.races;

import java.util.stream.IntStream;

public class DataRace {

    public static void main(String[] args) {
        final SharedClass sharedClass = new SharedClass();
        final Thread thread1 = new Thread(() -> IntStream.rangeClosed(0, Integer.MAX_VALUE).forEach(sharedClass::increment));
        final Thread thread2 = new Thread(() -> IntStream.rangeClosed(0, Integer.MAX_VALUE).forEach(sharedClass::checkForDataRace));

        thread1.start(); // increment first
        thread2.start(); // checking if x > y
    }

    public static class SharedClass {
//        int x = 0;
//        int y = 0;

        // solution
        volatile int x = 0;
        volatile int y = 0;

        public void increment(int n) { // will be called by thread 1
            x++;
            y++;
        }

        public void checkForDataRace(int n) { // will be called by thread 2
            if (y > x) {
                System.err.println("y > x - Data Race is detected");
            } else {
                System.err.println("No Data Race");
            }
        }
    }
}
