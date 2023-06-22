package searching;

public class BinarySearch {

    public static void main(String[] args) {
        String[] sample = {"Ames", "Bames", "Cames", "Es", "Dames", "James"};
        System.err.println("result: " + binarySearch(sample, "Es"));
    }

    public static int binarySearch(final String[] lookup, final String toLookFor) {
        int result = -1;
        int left = 0;
        int right = lookup.length - 1;

        while(left <= right) {
            int mid = left + ((right - left) / 2);
            System.err.println(left + "-" + mid + "-" + right);

            if (lookup[mid].equalsIgnoreCase(toLookFor)) {
                return mid;
            }

            if (lookup[mid].compareTo(toLookFor) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

}
