package edu.project4.Renderer;

import edu.project4.AffineTransformations;
import edu.project4.Records.FractalImage;
import edu.project4.Transformation.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadedRenderer implements Renderer {
    private final static int NUMBER_AFFINE = 5;

    private final int numberThread;

    public MultiThreadedRenderer(int numberThread) {
        if (numberThread <= 1) {
            throw new IllegalArgumentException();
        }

        this.numberThread = numberThread;
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        List<Transformation> variations,
        int symmetry,
        int samples,
        short iterPerSample,
        long seed
    ) {
        if (canvas == null || variations == null || variations.isEmpty() || symmetry <= 0 || samples <= 0
            || iterPerSample <= 0) {
            throw new IllegalArgumentException();
        }

        Random random = threadLocaleRandom(seed);
        AffineTransformations[] affine = AffineTransformations.create(NUMBER_AFFINE, random);

        ExecutorService executor = Executors.newFixedThreadPool(numberThread);
        int iterationsPerThread = samples / numberThread;

        for (int i = 0; i < numberThread; i++) {

            executor.execute(() -> {
                Random randomMultiThread = threadLocaleRandom(seed);

                Renderer.renderSample(
                    canvas,
                    iterPerSample,
                    symmetry,
                    iterationsPerThread,
                    affine,
                    randomMultiThread,
                    variations
                );
            });
        }

        try {
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return canvas;
    }

    private Random threadLocaleRandom(long seed) {
        Random random = ThreadLocal.withInitial(Random::new).get();
        random.setSeed(seed);

        return random;
    }
}
