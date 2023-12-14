package edu.project4;

import edu.project4.ImageProcessors.GammaCorrector;
import edu.project4.Records.FractalImage;
import edu.project4.Renderer.MultiThreadedRenderer;
import edu.project4.Renderer.Renderer;
import edu.project4.Renderer.SingleThreadedRenderer;
import edu.project4.Transformation.DiskTransformation;
import edu.project4.Transformation.LinearTransformation;
import edu.project4.Transformation.PolarTransformation;
import edu.project4.Transformation.SinusoidalTransformation;
import edu.project4.Transformation.SphericalTransformation;
import edu.project4.Transformation.Transformation;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"HideUtilityClassConstructor", "MagicNumber", "MultipleStringLiterals"})
public class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        FractalImage fractalImage = FractalImage.create(1920, 1080);
        List<Transformation> variations = List.of(
            new DiskTransformation(),
            new LinearTransformation(),
            new PolarTransformation(),
            new SinusoidalTransformation(),
            new SphericalTransformation()
        );

        Renderer rendererS = new SingleThreadedRenderer();
        GammaCorrector gammaCorrector = new GammaCorrector();

        LOGGER.info("Symmetry: " + 2 + " Samples: " + 10000 + " IterPerSample: " + 2000);

        long startTime1 = System.nanoTime();
        fractalImage = rendererS.render(fractalImage, variations, 2, 10000, (short) 2000, System.nanoTime());
        gammaCorrector.process(fractalImage);
        ImageUtils.save(fractalImage, Path.of("src/main/resources/fractal_image1"), ImageFormat.PNG);
        long endTime1 = System.nanoTime();
        long duration1 = (endTime1 - startTime1) / 1_000_000;

        LOGGER.info("Threads: " + 1 + ", Time: " + duration1 + " ms");

        for (int numOfThreads = 5; numOfThreads <= 15; numOfThreads++) {
            Renderer rendererM = new MultiThreadedRenderer(numOfThreads);

            long startTime = System.nanoTime();

            fractalImage = rendererM.render(fractalImage, variations, 2, 10000, (short) 10000, System.nanoTime());
            gammaCorrector.process(fractalImage);
            ImageUtils.save(fractalImage, Path.of("src/main/resources/fractal_image"), ImageFormat.PNG);

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            double speedup = (double) duration1 / duration;

            LOGGER.info("Threads: " + numOfThreads + ", Time: " + duration + " ms, Speedup: " + speedup);
        }
    }
}
