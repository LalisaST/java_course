package edu.hw7;


import edu.hw7.task2.ParallelFactorialCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParallelFactorialCalculatorTest {

    @Test
    @DisplayName("Проверка calculateFactorial")
    void threadSafetyCheck() {
        assertThat(ParallelFactorialCalculator.calculateFactorial(5)).isEqualTo(120);
    }

    @Test
    @DisplayName("Ввод некорректного значения")
    void enteringIncorrectNumberThreads() {
        assertThatThrownBy(() -> ParallelFactorialCalculator.calculateFactorial(0)).isInstanceOf(
            IllegalArgumentException.class);

    }
}
