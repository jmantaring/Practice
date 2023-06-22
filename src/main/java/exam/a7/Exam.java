package exam.a7;

public class Exam {

    public static void main(String[] args) {
        printRightTriangleStar(5);
        printPascalTriangle(5);
        printFibonacci(7);
        System.err.println(TriangleType.find(10, 5, 11));
        System.err.println(isPalindrome("ANnlA"));
    }

    public static void printRightTriangleStar(int depth) {

        for (int col = 0 ; col < depth ; col++) {
            for (int row = 0 ; row <= col ; row++) {
                System.err.print("*");
            }
            System.err.println();
        }

    }

    public static void printPascalTriangle(int depth) {
        for (int line = 1; line <= depth; line++) {
            for (int j = 0; j <= depth - line; j++) {
                System.out.print(" ");
            }

            int k = 1;
            for (int i = 1; i <= line; i++) {
                System.out.print(k + " ");
                k = k * (line - i) / i;
            }

            System.out.println();
        }
    }

    public static void printFibonacci(int length) {

        int first = 0;
        int second = 1;
        int temp;

        if (length >= 1) {
            System.err.print(first + " ");
        }

        if (length >= 2) {
            System.err.print(second + " ");
        }

        for (int i = 2 ; i < length ; i++) {
            temp = first + second;
            System.err.print(temp + " ");
            first = second;
            second = temp;
        }
    }

    enum TriangleType {

        SCALENE, ISOSCELES, EQUILATERAL, NOT_A_TRIANGLE;

        public static TriangleType find(int leftLength, int belowLength, int rightLength) {
            if (leftLength == rightLength && rightLength == belowLength) {
                return TriangleType.EQUILATERAL;
            } else if (leftLength == rightLength && rightLength != belowLength) {
                return TriangleType.ISOSCELES;
            } else if (leftLength != rightLength &&  rightLength != belowLength && leftLength != belowLength) {
                return TriangleType.SCALENE;
            } else {
                return TriangleType.NOT_A_TRIANGLE;
            }
        }

    }

    public static boolean isPalindrome(String text) {
        final String toCheck = text.toLowerCase();
        int left = 0;
        int right = toCheck.length() - 1;

        while (left <= right) {
            if (toCheck.charAt(left) != toCheck.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    /**
     *  1. Display the complete name of the player with their corresponding scores. Separate the first and last name with comma ",".
     *  SELECT CONCAT(LAST_NAME, ', ', FIRST_NAME) as NAME, SCORE
     *  FROM PLAYERS INNER JOIN SCORES ON PLAYERS.PLAYER_ID = SCORES.PLAYER_ID;
     *
     *  2. Display the player's name who has the highest scores
     *  SELECT CONCAT(LAST_NAME, ',', FIRST_NAME) as NAME, SCORE
     *  FROM PLAYERS INNER JOIN SCORES ON PLAYERS.PLAYER_ID = SCORES.PLAYER_ID
     *  WHERE score = (SELECT MAX(SCORE) FROM SCORES);
     *
     *  3. Display the player's name with scores >=30 and <=90
     *  SELECT CONCAT(LAST_NAME, ',', FIRST_NAME) as NAME, SCORE
     *  FROM PLAYERS INNER JOIN SCORES ON PLAYERS.PLAYER_ID = SCORES.PLAYER_ID
     *  WHERE score >= 30 AND score <= 90;
     *
     *  4. Display the count of players with same scores
     *  SELECT COUNT(SCORE) AS count, SCORE
     *  FROM SCORES
     *  GROUP BY SCORE;
     *
     *  5. Display the average scores of the players
     *  SELECT AVG(SCORE) AS AVERAGE FROM SCORES;
     */

}
