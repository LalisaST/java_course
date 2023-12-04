package edu.project4.Renderer;

import edu.project4.AffineTransformations;
import edu.project4.Records.FractalImage;
import edu.project4.Records.Pixel;
import edu.project4.Records.Point;
import edu.project4.Transformation.Transformation;
import java.util.List;
import java.util.Random;

@FunctionalInterface
public interface Renderer {
    Point MIN_P = new Point(-1.777, -1);
    Point MAX_P = new Point(1.777, 1);
    int SKIP_ITER = 20;

    FractalImage render(
        FractalImage canvas,
        List<Transformation> variations,
        int symmetry,
        int samples,
        short iterPerSample,
        long seed
    );

    static Point random(Random random) {
        double x = random.nextDouble(MIN_P.x(), MAX_P.x());
        double y = random.nextDouble(MIN_P.y(), MAX_P.y());
        return new Point(x, y);
    }

    static Point rotate(Point point, double theta) {
        double x = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        double y = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);
        return new Point(x, y);
    }

    static Point applyAffine(AffineTransformations affine, Point p) {
        double x = affine.a() * p.x() + affine.b() * p.y() + affine.c();
        double y = affine.d() * p.x() + affine.e() * p.y() + affine.f();

        return new Point(x, y);
    }

    static void coloring(Pixel pixel, AffineTransformations affine) {
        if (pixel == null) {
            throw new IllegalArgumentException();
        }

        int counter = pixel.getHitCount();

        if (counter == 0) {
            pixel.setR(affine.red());
            pixel.setG(affine.green());
            pixel.setB(affine.blue());

        } else {
            pixel.setR((pixel.getR() + affine.red()) / 2);
            pixel.setG((pixel.getG() + affine.green()) / 2);
            pixel.setB((pixel.getB() + affine.blue()) / 2);
        }

        pixel.setHitCount(counter + 1);
    }

    static void mapRange(Point point, FractalImage canvas, AffineTransformations affine) {

        int x = canvas.width() - (int) (((MAX_P.x() - point.x()) / (MAX_P.x() - MIN_P.x())) * canvas.width());
        int y = canvas.height() - (int) (((MAX_P.y() - point.y()) / (MAX_P.y() - MIN_P.y())) * canvas.height());

        if (x < canvas.width() && y < canvas.height()) {

            if (canvas.pixel(x, y) != null) {
                Renderer.coloring(canvas.pixel(x, y), affine);
            }
        }
    }

    static void renderSample(
        FractalImage canvas,
        short iterPerSample,
        int symmetry,
        int iterationsPerThread,
        AffineTransformations[] affine,
        Random random,
        List<Transformation> variations
    ) {
        for (int num = 0; num < iterationsPerThread; ++num) {
            Point pw = Renderer.random(random);

            for (int step = -SKIP_ITER; step < iterPerSample; ++step) {
                int affineRand = random.nextInt(0, affine.length);
                pw = Renderer.applyAffine(affine[affineRand], pw);

                Transformation variation = variations.get(random.nextInt(variations.size()));
                pw = variation.apply(pw);

                if (step > 0) {
                    double theta2 = 0.0;

                    for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                        var pwr = Renderer.rotate(pw, theta2);

                        if (pwr.x() >= MIN_P.x() && pwr.x() <= MAX_P.x()
                            && pwr.y() >= MIN_P.y() && pwr.y() <= MAX_P.y()) {
                            Renderer.mapRange(pwr, canvas, affine[affineRand]);
                        }
                    }
                }
            }
        }
    }
}
