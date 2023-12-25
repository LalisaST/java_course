package edu.project4.ImageProcessors;

import edu.project4.Records.FractalImage;
import edu.project4.Records.Pixel;
import static java.lang.StrictMath.log10;
import static java.lang.StrictMath.pow;

public class GammaCorrector implements ImageProcessor {
    private static final double GAMMA = 0.5;
    private double maxNormal = 0.0;

    @Override
    public void process(FractalImage image) {
        if (image == null) {
            throw new IllegalArgumentException();
        }

        logarithmicCorrection(image);
        gammaCorrection(image);
    }

    private void logarithmicCorrection(FractalImage image) {
        Pixel[] pixels = image.data();

        for (int i = 0; i < image.height() * image.width(); i++) {
            if (pixels[i].getHitCount() != 0) {
                Pixel pixel = pixels[i];

                double normal = log10(pixel.getHitCount());
                pixel.setNormal(normal);

                if (normal > maxNormal) {
                    maxNormal = normal;
                }
            }
        }
    }

    private void gammaCorrection(FractalImage image) {
        Pixel[] pixels = image.data();
        for (int i = 0; i < image.height() * image.width(); i++) {
            Pixel pixel = pixels[i];

            if (pixel.getHitCount() != 0) {
                double normal = pixel.getNormal() / maxNormal;
                pixel.setNormal(normal);
                pixel.setR((int) (pixel.getR() * pow(normal, (1.0 / GAMMA))));
                pixel.setG((int) (pixel.getG() * pow(normal, (1.0 / GAMMA))));
                pixel.setB((int) (pixel.getB() * pow(normal, (1.0 / GAMMA))));

            }

        }
    }
}
