package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Проверка сдвига вправо")
    void checkingRotateRight() {
        int number = 8;
        int shift = 1;

        int result = Task7.rotateRight(number,shift);

        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Проверка сдвига влвео")
    void checkingRotateLeft() {
        int number = 17;
        int shift = 2;

        int result = Task7.rotateLeft(number, shift);

        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("Ввод нуля")
    void enteringZero() {
        int number = 0;
        int shift = 5;

        int result = Task7.rotateLeft(number, shift);

        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Ввод без сдвига")
    void enteringWithoutShift() {
        int number = 9;
        int shift = 0;

        int result = Task7.rotateLeft(number, shift);

        assertThat(result).isEqualTo(9);
    }

    @Test
    @DisplayName("Ввод отрицательного числа")
    void enteringNegativeNumber() {
        int number = -10;
        int shift = 6;

        int result = Task7.rotateLeft(number, shift);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Ввод отрицательного сдвига")
    void enteringNegativeShift() {
        int number = 36;
        int shift = -5;

        int result = Task7.rotateLeft(number, shift);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Ввод большего сдвига")
    void enteringLargerShift() {
        int number = 10;
        int shift = 19;

        int result = Task7.rotateLeft(number, shift);

        assertThat(result).isEqualTo(5);
    }
}
