package threading.resource;

import java.util.stream.IntStream;

public class SharingResourceProblem {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.err.println(inventoryCounter.getItems());
    }

    public static class IncrementingThread extends Thread {
        private InventoryCounter inventoryCounter;
        public IncrementingThread(InventoryCounter inventoryCounter) { this.inventoryCounter = inventoryCounter; }
        @Override
        public void run() {
            IntStream.rangeClosed(1, 10000).forEach(index -> inventoryCounter.increment());
        }
    }

    public static class DecrementingThread extends Thread {
        private InventoryCounter inventoryCounter;
        public DecrementingThread(InventoryCounter inventoryCounter) { this.inventoryCounter = inventoryCounter; }
        @Override
        public void run() {
            IntStream.rangeClosed(1, 10000).forEach(index -> inventoryCounter.decrement());
        }
    }

    private static class InventoryCounter {
        private int items = 0;
        public void increment() { items++; }
        public void decrement() { items--; }
        public int getItems() { return items; }
    }

    // Solution
//    private static class InventoryCounter {
//        private int items = 0;
//        Object lock = new Object();
//        public void increment() {
//            // Critical section
//            synchronized (this.lock) {
//                items++;
//            }
//        }
//        public void decrement() {
//            // Critical section
//            synchronized (this.lock) {
//                items--;
//            }
//        }
//        public int getItems() {
//            synchronized (this.lock) {
//                return items;
//            }
//        }
//    }
}
