package edu.hw9.task1;

public class Calculation {
    private Calculation() {
    }

    public static double calculateAverage(double[] data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    public static double calculateSum(double[] data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum;
    }

    public static double findMax(double[] data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        double max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }

    public static double findMin(double[] data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        double min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }
}
