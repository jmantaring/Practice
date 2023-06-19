package java8.functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MastersExercise5 {

    public final static String FILE_PATH = "/Users/administrator/Documents/Workspace/Practice/Practice/resource/exercise5.txt";

    public static void main(String[] args) {

        try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))) {
            List<Details> details = stream.skip(1)
                    .map(rawDetail -> {
                        final Details detail = new Details(rawDetail);
                        print(detail);
                        return detail;
                    })
                    .collect(Collectors.toList());

            final Integer grandTotalBounty = details.stream()
                    .map(Details::getBounty)
                    .reduce(0, Math::addExact);

            System.err.println(grandTotalBounty);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print(Details details) {
        System.err.println(String.format("name: %s", details.name));
        System.err.println(String.format("location: %s", details.location));
        System.err.println(String.format("bounty: %d", details.bounty));
        System.err.println();
    }

    private static class Details {
        String name;
        String location;
        int bounty;

        public int getBounty() {
            return bounty;
        }

        public Details(String rawDetail) {
            final String[] splitRawDetail = rawDetail.split(",");
            validate(splitRawDetail);
            assign(splitRawDetail);
        }

        private void validate(String[] splitRawDetail) {
            if (splitRawDetail.length != 3) {
                throw new UnsupportedOperationException("Invalid detail length");
            }
        }

        private void assign(String[] splitRawDetail) {
            this.name = splitRawDetail[0];
            this.location = splitRawDetail[1];
            this.bounty = Integer.valueOf(splitRawDetail[2]);
        }
    }

}
