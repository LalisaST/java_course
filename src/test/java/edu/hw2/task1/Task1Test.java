package edu.hw2.task1;

import edu.hw2.task1.Expr.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Проверка Negate")
    void checkingNegate() {
        var negOne = new Negate(new Constant(1));

        assertThat(negOne.evaluate()).isEqualTo(-1);
    }

    @Test
    @DisplayName("Ввод null в Negate")
    void enteringNullNegate() {
        assertThatThrownBy(() -> {

            var negOne = new Negate(null);
            negOne.evaluate();

        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка Addition")
    void checkingAddition() {
        var two = new Constant(2);
        var four = new Constant(4);

        var sumTwoFour = new Addition(two, four);

        assertThat(sumTwoFour.evaluate()).isEqualTo(6);
    }

    @Test
    @DisplayName("Ввод null в Addition")
    void enteringAddition() {
        assertThatThrownBy(() -> {

            var two = new Constant(2);
            var sumTwoNull = new Addition(two, null);

            sumTwoNull.evaluate();

        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка Multiplication")
    void checkingMultiplication() {
        var minusOne = new Constant(-1);
        var four = new Constant(4);

        var mult = new Multiplication(minusOne, four);

        assertThat(mult.evaluate()).isEqualTo(-4);
    }

    @Test
    @DisplayName("Ввод null в Multiplication")
    void enteringNullMultiplication() {
        assertThatThrownBy(() -> {

            var two = new Constant(2);
            var mult = new Multiplication(two, null);

            mult.evaluate();

        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка Exponent")
    void checkingExponent() {
        var two = new Constant(2);
        var exp = new Exponent(two, 2);

        assertThat(exp.evaluate()).isEqualTo(4);
    }

    @Test
    @DisplayName("Ввод null в Exponent")
    void enteringNullExponent() {
        assertThatThrownBy(() -> {

            var exp = new Exponent(null, 2);

            exp.evaluate();

        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ввод некорректных значений в Exponent")
    void enteringIncorrectValuesExponent() {
        assertThatThrownBy(() -> {
            var minusTwo = new Constant(-2);
            var exp = new Exponent(minusTwo, 0.5);

            exp.evaluate();

        }).isInstanceOf(IllegalArgumentException.class);


    }

}
