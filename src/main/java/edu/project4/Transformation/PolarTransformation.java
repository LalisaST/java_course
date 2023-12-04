package edu.project4.Transformation;

import edu.project4.Records.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }

        double theta = Math.atan2(point.y(), point.x());
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        return new Point(theta / Math.PI, r - 1);
    }
}

