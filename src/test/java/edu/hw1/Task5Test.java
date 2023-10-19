package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Проверка палиндрома")
    void checkingPalindrome() {
        int number = 11211230;

        boolean result = Task5.isPalindromeDescendant(number);

        assertThat(result).isEqualTo(true );
    }

    @Test
    @DisplayName("Проверка трехзначного числа")
    void checkingThreeDigitNumber() {
        int number = 448;

        boolean result = Task5.isPalindromeDescendant(number);

        assertThat(result).isEqualTo(true );
    }

    @Test
    @DisplayName("Проверка не палиндрома")
    void checkingNonPalindrome() {
        int number = 10;

        boolean result = Task5.isPalindromeDescendant(number);

        assertThat(result).isEqualTo(false );
    }

    @Test
    @DisplayName("Проверка длины числа")
    void checkingLengthNumber() {
        int number = 5;

        boolean result = Task5.isPalindromeDescendant(number);

        assertThat(result).isEqualTo(false );
    }

    @Test
    @DisplayName("Проверка отрицательного числа")
    void checkingNegativeNumber() {
        int number = -23336014;

        boolean result = Task5.isPalindromeDescendant(number);

        assertThat(result).isEqualTo(true );
    }
}
