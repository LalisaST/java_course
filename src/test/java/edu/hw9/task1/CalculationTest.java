package edu.hw9.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CalculationTest {
    @Test
    @DisplayName("Проверка расчетов")
    void checkingCalculations() {
        double[] arr = {2.0, 9.0, 3.0, 5.0, 6.0};

        assertThat(Calculation.calculateSum(arr)).isEqualTo(25.0);
        assertThat(Calculation.calculateAverage(arr)).isEqualTo(5.0);
        assertThat(Calculation.findMax(arr)).isEqualTo(9.0);
        assertThat(Calculation.findMin(arr)).isEqualTo(2.0);
    }

    @Test
    @DisplayName("Ввод некорректных значений в функции")
    void enteringIncorrectValuesInFunction() {
        assertThatThrownBy(() -> Calculation.calculateSum(null)).isInstanceOf(
            IllegalArgumentException.class);

        assertThatThrownBy(() -> Calculation.calculateAverage(null)).isInstanceOf(
            IllegalArgumentException.class);

        assertThatThrownBy(() -> Calculation.findMax(null)).isInstanceOf(
            IllegalArgumentException.class);

        assertThatThrownBy(() -> Calculation.findMin(null)).isInstanceOf(
            IllegalArgumentException.class);
    }
}
