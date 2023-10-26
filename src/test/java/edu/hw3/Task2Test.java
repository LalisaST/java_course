package edu.hw3;

import edu.hw3.task2.Clusterize;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task2Test {
    @Test
    @DisplayName("Ввод null")
    void enteringNull() {

        assertThatThrownBy(() -> {
            Clusterize.clusterize(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> provideIncorrectValues() {
        return Stream.of(
            Arguments.of("()(()"),
            Arguments.of("())(())"),
            Arguments.of("(((((")
        );
    }

    @DisplayName("Проверка некорректных значений")
    @ParameterizedTest
    @MethodSource("provideIncorrectValues")
    void checkingIncorrectValues(String str) {
        assertThatThrownBy(() -> {
            Clusterize.clusterize(str);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> provideCorrectValues() {
        return Stream.of(
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))")),
            Arguments.of("", List.of()),
            Arguments.of(" ", List.of()),
            Arguments.of(" ()      ", List.of("()"))

        );
    }

    @DisplayName("Проверка корректных значений")
    @ParameterizedTest
    @MethodSource("provideCorrectValues")
    void checkingCorrectValues(String str, List<String> result) {
        assertThat(Clusterize.clusterize(str)).isEqualTo(result);
    }
}
