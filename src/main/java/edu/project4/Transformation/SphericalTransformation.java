package edu.project4.Transformation;

import edu.project4.Records.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }

        double r = point.x() * point.x() + point.y() * point.y();
        return new Point(point.x() / r, point.y() / r);
    }
}
