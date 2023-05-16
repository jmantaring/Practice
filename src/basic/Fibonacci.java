package basic;

public class Fibonacci {

    public static void main(String[] args) {
//        printFibonacci(10);
        printOptimizedFibonacci(10);
//        printFibo(1);
//        printFibo(2);
//        printFibo(3);
//        printFibo(0);
//        printFibo(3);
    }

    public static void printFibo(int n) {
        int temp;
        int num1 = 0;
        int num2 = 1;
        if(n>=1){
            System.out.print(num1 + " ");
        }
        if(n>1){
            System.out.print(num2 + " ");
        }
        for(int i = 2; i<n; --n){
            temp = num1 + num2;
            System.out.print(temp + " ");
            num1 = num2;
            num2 = temp;
        }
    }

    public static void printFibonacci(int n) {
        int first = 0;
        int second = 1;
        int temp = 1;

        for (int i = 0; i < n; i++) {
            System.err.print(first + " ");
            first = second;
            second = temp;
            temp = first + second;
        }
        System.err.println();
    }

    public static void printOptimizedFibonacci(int n) {
        int first = 0;
        int second = 1;
        int temp;

        if (n > 0) {
            System.err.print(first + " ");
        }

        if (n > 1) {
            System.err.print(second + " ");
        }

        for (int index = 2;index < n; index++) {
            temp = first + second;
            System.err.print(temp + " ");
            first = second;
            second = temp;
        }
        System.err.println();
    }

}
