package edu.hw2.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Расчет площади квадрата")
    void calculationSquareArea() {
        Square square = new Square(4);

        assertThat(square.area()).isEqualTo(16);
    }

    @Test
    @DisplayName("Расчет площади прямоугольника")
    void calculationRectangleArea() {
        Rectangle rectangle = new Rectangle(20, 10);

        assertThat(rectangle.area()).isEqualTo(200);
    }

    @Test
    @DisplayName("Ввод максимального значения")
    void enteringMaxValue() {
        int number = Integer.MAX_VALUE;
        Rectangle rectangle = new Rectangle(number, number);

        assertThat(rectangle.area()).isEqualTo(number * number);
    }

    @Test
    @DisplayName("Изменение высоты")
    void changeHeight() {
        Square square = new Square(4);
        Rectangle rectangle = square.setHeight(-4);

        assertThat(rectangle.area()).isEqualTo(-16);
    }

    @Test
    @DisplayName("Изменение ширины")
    void changeWidth() {
        Square square = new Square(2);
        Rectangle rectangle = square.setWidth(6);

        assertThat(rectangle.area()).isEqualTo(12);
    }

    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle(1, 1)),
            Arguments.of(new Square(1))
        };
    }

    @DisplayName("Проверка подстановки")
    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }
}
