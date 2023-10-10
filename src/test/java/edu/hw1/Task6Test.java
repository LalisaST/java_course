package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Подсчет корректного числа")
    void countingCorrectNumber() {
        int number = 6621;

        int result = Task6.countK(number);

        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Проверка числа ниже минимального")
    void checkingMinimalityNumber() {
        int number = 1000;

        int result = Task6.countK(number);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка шнапсового числа")
    void checkingSchnappsNumber() {
        int number = 5555;

        int result = Task6.countK(number);

        assertThat(result).isEqualTo(-1);
    }
}
