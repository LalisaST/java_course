package edu.project4;

import edu.project4.Records.FractalImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FractalImageTest {
    @Test
    @DisplayName("Ввод некорректных аргументов")
    void enteringIncorrectArguments() {

        assertThatThrownBy(() -> FractalImage.create(0, 1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> FractalImage.create(1, 0))
            .isInstanceOf(IllegalArgumentException.class);

    }
}
