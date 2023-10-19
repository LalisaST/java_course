package edu.hw1;

public class Task6 {
    static int counter;
    static final int MIN_VALUE = 1000;
    static final int SIMILAR = 1111;
    static final int DIFFERENCE = 6174;

    private Task6() {

    }

    public static int countK(int number) {
        if (number <= MIN_VALUE || number % SIMILAR == 0) {
            return -1;
        }

        int min;
        int max;

        String numberStr = String.valueOf(number);
        char[] numberChar = numberStr.toCharArray();
        java.util.Arrays.sort(numberChar);
        min = Integer.parseInt(new String(numberChar));

        char[] revers = numberStr.toCharArray();
        for (int i = 0; i < numberChar.length; i++) {
            revers[i] = numberChar[numberChar.length - i - 1];
        }
        max = Integer.parseInt(new String(revers));

        if (max - min != DIFFERENCE) {
            if (countK(max - min) == -1) {
                return -1;
            }
            counter++;
        }

        return counter + 1;
    }
}
