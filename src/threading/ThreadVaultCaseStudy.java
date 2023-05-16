package threading;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ThreadVaultCaseStudy {

    private final static int MAX_PASSWORD = 9999;

    public static void main(String[] args) {
        Random random = new Random();

        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        final List<Thread> threads = Arrays.asList(
                new AscendingHackerThread(vault),
                new DescendingHackerThread(vault),
                new PoliceThread()
        );

        threads.stream()
                .forEach(Thread::start);
    }

    private static class Vault {
        private int password;
        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {}
            return this.password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;
        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.err.println("Starting Thread " + this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {
                if (this.vault.isCorrectPassword(guess)) {
                    System.err.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess > MAX_PASSWORD; guess--) {
                if (this.vault.isCorrectPassword(guess)) {
                    System.err.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                System.err.println(i);
            }

            System.err.println("Game over for you hackers");
            System.exit(0);
        }
    }

}
