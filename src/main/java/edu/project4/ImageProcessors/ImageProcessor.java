package edu.project4.ImageProcessors;

import edu.project4.Records.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
