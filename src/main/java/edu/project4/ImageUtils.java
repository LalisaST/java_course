package edu.project4;

import edu.project4.Records.FractalImage;
import edu.project4.Records.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    static final int SIXTEEN = 16;
    static final int EIGHT = 8;

    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        if (image == null || filename == null || format == null) {
            throw new IllegalArgumentException();
        }

        BufferedImage bufferedImage = convertFractalImageToBufferedImage(image);

        try {
            File outputFile = new File(filename + "." + format.name().toLowerCase());
            ImageIO.write(bufferedImage, format.name(), outputFile);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static BufferedImage convertFractalImageToBufferedImage(FractalImage image) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                int rgb = (pixel.getR() << SIXTEEN) | (pixel.getG() << EIGHT) | pixel.getB();
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        return bufferedImage;
    }
}
