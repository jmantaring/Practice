package java8.functional;

import java.util.function.Function;

public class MastersExercise1 {

    public static void main(String[] args) {

        Function<String, Integer> lengthCounter = toCount -> toCount.length();
        Function<String, Integer> lettersCounter = toCount -> {
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            int counter = 0;
            for (int i = 0; i < toCount.length(); i++) {
                if (alphabet.contains(String.valueOf(toCount.toLowerCase().charAt(i)))) {
                    counter++;
                }
            }

            return counter;
        };

        System.err.println(lengthCounter.apply("There are 8 planets in the solar system!"));
        System.err.println(lettersCounter.apply("There are 8 planets in the solar system!"));

    }

    @FunctionalInterface
    public interface Counter {
        int count(final String toCount);
    }

}
