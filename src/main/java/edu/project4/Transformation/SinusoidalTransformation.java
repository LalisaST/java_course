package edu.project4.Transformation;

import edu.project4.Records.Point;

public class SinusoidalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }

        double x = Math.sin(point.x());
        double y = Math.sin(point.y());
        return new Point(x, y);
    }
}
