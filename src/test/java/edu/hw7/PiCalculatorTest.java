package edu.hw7;

import edu.hw7.task4.PiCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PiCalculatorTest {
    @Test
    @DisplayName("Ввод некорректных значений")
    void enteringIncorrectNumberThreads() {
        assertThatThrownBy(() -> PiCalculator.calculateMultiThread(100, 1)).isInstanceOf(
            IllegalArgumentException.class);
        assertThatThrownBy(() -> PiCalculator.calculateMultiThread(0, 2)).isInstanceOf(
            IllegalArgumentException.class);
        assertThatThrownBy(() -> PiCalculator.calculateSingleThread(0)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Тест calculateSingleThread")
    void testCalculateSingleThread() {
        assertEquals(3.14, PiCalculator.calculateSingleThread(10_000_000), 0.1);
    }

    @Test
    @DisplayName("Тест calculateMultiThread")
    void testCalculateMultiThread() {
        assertEquals(3.14, PiCalculator.calculateMultiThread(10_000_000, 4), 0.1);
    }
}
