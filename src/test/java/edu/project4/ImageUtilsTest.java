package edu.project4;

import edu.project4.Records.FractalImage;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageUtilsTest {

    @Test
    @DisplayName("Проверка создания файла")
    void checkingFileCreation() {

        FractalImage fractalImage = FractalImage.create(1, 1);
        Path filePath = Path.of("src/test/resources/fractal_image");

        ImageUtils.save(fractalImage, filePath, ImageFormat.PNG);

        assertThat(Path.of("src/test/resources/fractal_image.png")).exists();
    }

    @Test
    @DisplayName("Ввод некорректных аргументов")
    void enteringIncorrectArguments() {

        FractalImage fractalImage = FractalImage.create(1, 1);
        Path filePath = Path.of("src/test/resources/fractal_image");
        ImageFormat format = ImageFormat.PNG;

        assertThatThrownBy(() -> ImageUtils.save(fractalImage, null, format))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ImageUtils.save(fractalImage, filePath, null))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ImageUtils.save(null, filePath, format))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
