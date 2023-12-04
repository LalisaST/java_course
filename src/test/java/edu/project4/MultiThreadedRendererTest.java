package edu.project4;

import edu.project4.Records.FractalImage;
import edu.project4.Records.Pixel;
import edu.project4.Renderer.MultiThreadedRenderer;
import edu.project4.Renderer.Renderer;
import edu.project4.Transformation.SphericalTransformation;
import edu.project4.Transformation.Transformation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MultiThreadedRendererTest {
    @Test
    @DisplayName("Проверка рендеринга")
    void checkingRenderer() {
        FractalImage fractalImage = FractalImage.create(1, 1);
        List<Transformation> variations = List.of(new SphericalTransformation());
        Renderer rendererS = new MultiThreadedRenderer(2);
        FractalImage fractalImageResult = rendererS.render(fractalImage, variations, 1, 1, (short) 1, 1);

        assertThat(fractalImageResult).isNotNull();
        assertThat(fractalImageResult.height()).isEqualTo(fractalImage.height());
        assertThat(fractalImageResult.width()).isEqualTo(fractalImage.width());
        assertThat(fractalImageResult.data()).anyMatch(pixel -> !pixel.equals(new Pixel(0, 0, 0, 0, 0)));
    }

    @Test
    @DisplayName("Ввод некорректных аргументов")
    void enteringIncorrectArguments() {
        FractalImage fractalImage = FractalImage.create(1, 1);
        List<Transformation> variations = List.of(new SphericalTransformation());
        Renderer rendererS = new MultiThreadedRenderer(2);

        assertThatThrownBy(() -> new MultiThreadedRenderer(1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> rendererS.render(null, variations, 1, 1, (short) 1, 1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> rendererS.render(fractalImage, null, 1, 1, (short) 1, 1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> rendererS.render(fractalImage, variations, 0, 1, (short) 1, 1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> rendererS.render(fractalImage, variations, 1, 0, (short) 1, 1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> rendererS.render(fractalImage, variations, 1, 1, (short) 0, 1))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
