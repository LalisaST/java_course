package edu.hw3;

import edu.hw3.task3.FreqDict;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task3Test {
    static Stream<Arguments> provideListStrings() {
        return Stream.of(
            Arguments.of(
                List.of("a", "bb", "a", "bb"),
                Map.of("bb", 2, "a", 2)),
            Arguments.of(
                List.of("this", "and", "that", "and"),
                Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.of(
                List.of("код", "код", "код", "bug"),
                Map.of("код", 3, "bug", 1)),
            Arguments.of(
                List.of(1, 1, 2, 2),
                Map.of(1, 2, 2, 2)),
            Arguments.of(
                List.of(),
                Map.of())
        );
    }

    @DisplayName("Проверка списка String")
    @ParameterizedTest
    @MethodSource("provideListStrings")
    void validStringsTest(List list, Map map) {
        assertThat(FreqDict.freqDict(list)).isEqualTo(map);
    }

    @Test
    @DisplayName("Ввод листа = null")
    void enteringListNull() {
        assertThatThrownBy(() -> {
            List<String> list = null;
            FreqDict.freqDict(list);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Передача некорректного листа")
    void sendingIncorrectSheet() {
        assertThatThrownBy(() -> {
            List<String> list = Arrays.asList("ad", "ad", null, null);
            FreqDict.freqDict(list);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
