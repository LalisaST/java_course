package edu.project4;

import edu.project4.ImageProcessors.GammaCorrector;
import edu.project4.Records.FractalImage;
import edu.project4.Records.Pixel;
import edu.project4.Renderer.Renderer;
import edu.project4.Renderer.SingleThreadedRenderer;
import edu.project4.Transformation.SphericalTransformation;
import edu.project4.Transformation.Transformation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GammaCorrectorTest {
    @Test
    @DisplayName("Проверка гамма-коррекции")
    void checkingGammaCorrection() {
        FractalImage fractalImage = FractalImage.create(1, 1);
        List<Transformation> variations = List.of(new SphericalTransformation());
        Renderer rendererS = new SingleThreadedRenderer();

        FractalImage fractalImageResult = rendererS.render(fractalImage, variations, 1, 1, (short) 1, 1);
        new GammaCorrector().process(fractalImage);

        assertThat(fractalImageResult).isNotNull();
        assertThat(fractalImageResult.height()).isEqualTo(fractalImage.height());
        assertThat(fractalImageResult.width()).isEqualTo(fractalImage.width());
        assertThat(fractalImageResult.data()).anyMatch(pixel -> !pixel.equals(new Pixel(0, 0, 0, 0, 0)));
    }

    @Test
    @DisplayName("Ввод некорректных аргументов")
    void enteringIncorrectArguments() {
        assertThatThrownBy(() -> new GammaCorrector().process(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
