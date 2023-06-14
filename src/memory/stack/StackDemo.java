package memory.stack;

/**
 * Add breakpoints at variables x, y, and result
 * Debug me on intellij
 * Open frames/variables tab to observe.
 */
public class StackDemo {

    public static void main(String[] args) {
        int x = 1;
        int y = 2;
        int result = sum(x, y);
    }

    private static int sum(int a, int b) {
        int s = a + b;
        return s;
    }

}
