package edu.hw5;

import edu.hw5.task1.AverageTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AverageTimeTest {
    @Test
    @DisplayName("Проверка корректных данных")
    void checkingCorrectData() {
        var list = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );

        assertThat(AverageTime.averageTimeSearcher(list)).isEqualTo("3ч 40м");
    }

    static Arguments[] provideList() {
        return new Arguments[] {
            Arguments.of(Arrays.asList("2022-03-12, 20:20 - 2022-03-12, 23:50", null))
        };
    }

    @DisplayName("Проверка некорректных данных")
    @ParameterizedTest
    @NullSource
    @MethodSource("provideList")
    void checkingIncorrectData(List<String> dataTime) {

        assertThatThrownBy(() -> AverageTime.averageTimeSearcher(dataTime)).isInstanceOf(IllegalArgumentException.class);
    }
}
