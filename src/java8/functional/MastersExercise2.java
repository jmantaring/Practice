package java8.functional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MastersExercise2 {

    /**
     * Create a function that will accept an array of integers. Remove the numbers greater than 100.
     * Sort the remaining numbers from highest to lowest then
     *  transform the numbers to double and then display the numbers and output the first middle number.
     */
    public static void main(String[] args) {
        Integer[] numArr = { 7, 5, 901, 175, 8, 100, 21, 16, 3, 26, 34, 89, 82, 1, 1000, 101 };
        Integer[] numArr2 = { 7, 5, 8, 901, 175, 100, 21, 16, 3, 26, 34, 89, 1, 1000, 101 };
        solution(numArr);
        solution(numArr2);
    }

    public static void solution(Integer[] numArr) {

        final List<Double> result = Arrays.stream(numArr)
                .filter(num -> num <= 100)
                .sorted(Comparator.reverseOrder())
                .map(Double::valueOf)
                .collect(Collectors.toList());

        System.err.println(result);

        final Optional<Double> middleElementOptional = result.stream()
                .skip((result.size() -1) / 2)
                .findFirst();

        middleElementOptional
                .ifPresent(System.err::println);
    }

}
