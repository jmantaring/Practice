package threading.stopping;

import java.io.IOException;

public class ThreadWithSystemInput {

    public static void main(String[] args) {
        Thread thread = new Thread(new WaitingForUserInput());
        thread.setName(WaitingForUserInput.class.getName());
        thread.start();

        // Will not stop the app since thread is not a daemon and no handling for interruption
        thread.interrupt();
    }

    private static class WaitingForUserInput implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    final char input = (char) System.in.read();
                    if (input == 'q') {
                        return;
                    } else if (input == 'z') { // Will stop the app since we threw exception
                        throw new IOException("Entered z so throwing IOException");
                    }
                    System.err.println("Entered: " + input);
                }
            } catch (IOException e) {
                System.err.println("An exception was caught " + e.getMessage());
            }
        }
    }

}
