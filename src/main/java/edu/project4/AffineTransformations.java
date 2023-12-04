package edu.project4;

import java.util.Random;

public record AffineTransformations(
    double a,
    double b,
    double c,
    double d,
    double e,
    double f,
    int red,
    int green,
    int blue
) {
    private static final int MIN = -1;
    private static final int MAX = 1;
    private static final int COLOR = 256;

    public static AffineTransformations[] create(int number, Random random) {
        if (number <= 0 || random == null) {
            throw new IllegalArgumentException();
        }
        AffineTransformations[] affineTransformations = new AffineTransformations[number];

        for (int i = 0; i < number; i++) {
            affineTransformations[i] = new AffineTransformations(
                random.nextDouble(MIN, MAX),
                random.nextDouble(MIN, MAX),
                random.nextDouble(MIN, MAX),
                random.nextDouble(MIN, MAX),
                random.nextDouble(MIN, MAX),
                random.nextDouble(MIN, MAX),
                random.nextInt(0, COLOR),
                random.nextInt(0, COLOR),
                random.nextInt(0, COLOR)
            );
        }

        return affineTransformations;
    }
}
