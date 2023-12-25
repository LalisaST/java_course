package edu.project4.Transformation;

import edu.project4.Records.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }

        return point;
    }
}
