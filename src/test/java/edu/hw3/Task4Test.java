package edu.hw3;

import edu.hw3.task4.ConvertToRoman;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task4Test {
    static Stream<Arguments> provideIncorrectValues() {
        return Stream.of(
            Arguments.of(0),
            Arguments.of(-50),
            Arguments.of(5000)
        );
    }

    @DisplayName("Проверка некорректных значений")
    @ParameterizedTest
    @MethodSource("provideIncorrectValues")
    void checkingIncorrectValues(int number) {
        assertThatThrownBy(() -> {
            ConvertToRoman.convertToRoman(number);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> provideCorrectValues() {
        return Stream.of(
            Arguments.of(1, "I"),
            Arguments.of(3999, "MMMCMXCIX"),
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI")
        );
    }

    @DisplayName("Проверка корректных значений")
    @ParameterizedTest
    @MethodSource("provideCorrectValues")
    void checkingCorrectValues(int number, String result) {
        assertThat(ConvertToRoman.convertToRoman(number)).isEqualTo(result);
    }
}
