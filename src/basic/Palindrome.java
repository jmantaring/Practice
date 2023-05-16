package basic;

public class Palindrome {

    public static void main(String[] args) {
        final String ana = "Anna";
        System.err.println(String.format("Word %s test result of palindrome %s", ana, isPalindrome(ana)));
        System.err.println(String.format("Word %s test result of palindrome %s", ana, isPalindromeStartEndChecking(ana)));
    }

    public static boolean isPalindrome(final String toTest) {
        StringBuilder reverseInput = new StringBuilder();
        for(int i = (toTest.length()-1); i>=0; --i){
            reverseInput.append(toTest.charAt(i));
        }
        return reverseInput.toString().equalsIgnoreCase(toTest);
    }

    public static boolean isPalindromeStartEndChecking(final String toTest) {
        int start = 0;
        int end = toTest.length() - 1;

        while (start <= end) {
            if (toTest.charAt(start++) != toTest.charAt(end--)) {
                return false;
            }
        }

        return true;
    }

}
