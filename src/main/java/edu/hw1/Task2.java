package edu.hw1;

public class Task2 {
    static final int BASE = 10;

    private Task2() {

    }

    public static int countDigits(int number) {
        int lenght = 0;
        long temp = 1;
        int num = number;

        if (num < 0) {
            num = num * (-1);
        }

        if (num == 0) {
            return 1;
        }

        while (temp <= num) {
            lenght++;
            temp *= BASE;
        }
        return lenght;
    }
}
