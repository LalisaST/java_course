package edu.project4.Renderer;

import edu.project4.AffineTransformations;
import edu.project4.Records.FractalImage;
import edu.project4.Transformation.Transformation;
import java.util.List;
import java.util.Random;

public class SingleThreadedRenderer implements Renderer {
    private final static int NUMBER_AFFINE = 4;

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

        Random random = new Random(seed);
        AffineTransformations[] affine = AffineTransformations.create(NUMBER_AFFINE, random);

        Renderer.renderSample(
            canvas,
            iterPerSample,
            symmetry,
            samples,
            affine,
            random,
            variations
        );
        return canvas;
    }

}
