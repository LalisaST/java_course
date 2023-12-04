package edu.project4.Transformation;

import edu.project4.Records.Point;

public class DiskTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }

        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());

        double newX = (theta / Math.PI) * Math.sin(Math.PI * r);
        double newY = (theta / Math.PI) * Math.cos(Math.PI * r);

        return new Point(newX, newY);
    }
}
