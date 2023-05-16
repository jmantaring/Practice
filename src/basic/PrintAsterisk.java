package basic;

public class PrintAsterisk {


    public static void main(String[] args) {
        process(5);
    }

    public static void process(int number) {
        for (int i = 0; i < number; i++) {
            for (int j = 0; j <= i; j++) {
                System.err.print("*");
            }
            System.err.println();
        }
    }

}
