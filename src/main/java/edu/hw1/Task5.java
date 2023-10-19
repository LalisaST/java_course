package edu.hw1;

public class Task5 {
    static final int MIN_TWO_DIGIT = 10;

    private Task5() {

    }

    public static boolean isPalindromeDescendant(int number) {
        int num = number;

        if (num < 0) {
            num = num * (-1);
        }

        if (num <= MIN_TWO_DIGIT) {
            return false;
        }

        String numStr = String.valueOf(num);
        while (numStr.length() > 1) {
            if (!isPalindrome(numStr)) {
                numStr = findDescendant(numStr);
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean isPalindrome(String numStr) {
        int left = 0;
        int right = numStr.length() - 1;
        while (left < right) {
            if (numStr.charAt(left) != numStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static String findDescendant(String numStr) {
        StringBuilder descendant = new StringBuilder();
        for (int i = 0; i < numStr.length() - 1; i += 2) {
            int digit1 = Character.getNumericValue(numStr.charAt(i));
            int digit2 = Character.getNumericValue(numStr.charAt(i + 1));
            int sum = digit1 + digit2;
            descendant.append(sum);
        }
        if (numStr.length() % 2 != 0) {
            descendant.append(Character.getNumericValue(numStr.charAt(numStr.length() - 1)));
        }
        return descendant.toString();
    }
}
