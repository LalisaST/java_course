package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Ввод отрицательного числа")
    void enteringNegativeNumber() {
        int number = -10;

        int result = Task2.countDigits(number);

        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("Ввод положительное числа")
    void enteringPositiveNumber() {
        int number = 4666;

        int result = Task2.countDigits(number);

        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Ввод максимального значения")
    void enteringMax() {
        int number = Integer.MAX_VALUE;

        int result = Task2.countDigits(number);

        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("Ввод нуля")
    void enteringZero() {
        int number = 0;

        int result = Task2.countDigits(number);

        assertThat(result).isEqualTo(1);
    }
}
