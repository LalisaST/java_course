package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Task1Test {
    @Test
    @DisplayName("Ввод пустой строки")
    void enteringEmptyString() {
        String time = "";

        int result = Task1.minutesToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Неполный ввод")
    void incompleteInput() {
        String time = ":";

        int result = Task1.minutesToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка корректной строчки")
    void checkingCorrectLine() {
        String time = "01:00";

        int result = Task1.minutesToSeconds(time);

        assertThat(result).isEqualTo(60);
    }

    @Test
    @DisplayName("Проверка не корректной строчки")
    void checkingAnCorrectLine() {
        String time = "b:00";

        int result = Task1.minutesToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка секунд")
    void checkingSeconds() {
        String time = "10:60";

        int result = Task1.minutesToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка минут")
    void checkingMinute() {
        String time = "35791394:59";

        int result = Task1.minutesToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Ввод null")
    void enteringNull() {
        String time = null;

        int result = Task1.minutesToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }
}
