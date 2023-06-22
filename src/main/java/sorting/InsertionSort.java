package sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        String[] sample = {"Ames", "Bames", "Cames", "Es", "Dames"};
        System.err.println(Arrays.toString(sort(sample)));
    }

    public static String[] sort(final String[] toSort) {
        final String[] result = Arrays.copyOf(toSort, 5);

        for (int index = 1; index < result.length; ++index) {
            System.err.println("Iteration number: " + index);
            String key = result[index];
            int innerIndex = index - 1;

            while (innerIndex >= 0 && result[innerIndex].compareTo(key) > 0) {
                System.err.println("-----Inner Loop:-----");
                result[innerIndex + 1] = result[innerIndex];
                innerIndex--;
                System.err.println(Arrays.toString(result));
            }
            System.err.println("--------------------");

            result[innerIndex + 1] = key;
        }

        return result;
    }

}
