package edu.project4;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AffineTransformationsTest {
    @Test
    @DisplayName("Ввод некорректных аргументов")
    void enteringIncorrectArguments() {

        assertThatThrownBy(() -> AffineTransformations.create(0, new Random()))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> AffineTransformations.create(1, null))
            .isInstanceOf(IllegalArgumentException.class);

    }
}
