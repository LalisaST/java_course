package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockCatalog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task6Test {
    @Test
    @DisplayName("Ввод null в функцию add")
    void enteringNullAdd() {

        assertThatThrownBy(() -> {
            StockCatalog stockCatalog = new StockCatalog();
            stockCatalog.add(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ввод null в функцию remove")
    void enteringNullRemove() {

        assertThatThrownBy(() -> {
            StockCatalog stockCatalog = new StockCatalog();
            stockCatalog.remove(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка mostValuableStock")
    void checkingMostValuableStock() {
        StockCatalog stockCatalog = new StockCatalog();
        stockCatalog.add(new Stock(1000, "Comp1"));
        stockCatalog.add(new Stock(230, "Comp2"));
        stockCatalog.add(new Stock(300, "Comp3"));
        stockCatalog.add(new Stock(2674, "Comp4"));
        stockCatalog.add(new Stock(1234, "Comp5"));
        assertThat(stockCatalog.mostValuableStock().price()).isEqualTo(2674);
    }

    static Stream<Arguments> provideIncorrectValues() {
        return Stream.of(
            Arguments.of(100, null),
            Arguments.of(10, ""),
            Arguments.of(0, "Comp"),
            Arguments.of(-100, "Comp"),
            Arguments.of(58, "       ")
        );
    }

    @DisplayName("Проверка некорректных значений")
    @ParameterizedTest
    @MethodSource("provideIncorrectValues")
    void checkingIncorrectValues(int price, String name) {
        assertThatThrownBy(() -> {
            new Stock(price, name);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
