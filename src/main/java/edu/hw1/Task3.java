package edu.hw1;

public class Task3 {

    private Task3() {

    }

    public static boolean isNestable(int[] array1, int[] array2) {
        if (array1 == null || array2 == null || array1.length == 0 || array2.length == 0) {
            return false;
        }

        int min1 = array1[0];
        int max1 = array1[0];
        int min2 = array2[0];
        int max2 = array2[0];

        for (int i = 1; i < array1.length; i++) {
            if (array1[i] < min1) {
                min1 = array1[i];
            }
            if (array1[i] > max1) {
                max1 = array1[i];
            }
        }

        for (int i = 1; i < array2.length; i++) {
            if (array2[i] < min2) {
                min2 = array2[i];
            }
            if (array2[i] > max2) {
                max2 = array2[i];
            }
        }

        return (min1 > min2) && (max1 < max2);
    }
}
