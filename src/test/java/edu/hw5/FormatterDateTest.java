package edu.hw5;

import edu.hw5.task3.FormatterDate;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FormatterDateTest {
    static Arguments[] provideDate() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate daysAgo = LocalDate.now().minusDays(2234);

        return new Arguments[] {
            Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3, 1))),
            Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10))),
            Arguments.of("2020-12-2", Optional.of(LocalDate.of(2020, 12, 2))),
            Arguments.of("1/3/20", Optional.of(LocalDate.of(2020, 3, 1))),
            Arguments.of("1 day ago", Optional.of(yesterday)),
            Arguments.of("2234 days ago", Optional.of(daysAgo)),
            Arguments.of("tomorrow", Optional.of(tomorrow)),
            Arguments.of("today", Optional.of(today)),
            Arguments.of("yesterday", Optional.of(yesterday))
        };
    }

    @DisplayName("Проверка форматов")
    @ParameterizedTest
    @MethodSource("provideDate")
    void checkingFormats(String date, Optional<LocalDate> optionalLocalDate) {

        assertThat(FormatterDate.parseDate(date)).isEqualTo(optionalLocalDate);
    }

    @Test
    @DisplayName("Проверка некорректных значений")
    void checkingFormats() {
        assertThatThrownBy(() -> FormatterDate.parseDate(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> FormatterDate.parseDate("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка непарвильных форматов")
    void checkingWrongFormat() {
        assertThat(FormatterDate.parseDate(" ")).isEqualTo(Optional.empty());
        assertThat(FormatterDate.parseDate("10/13-2023 ")).isEqualTo(Optional.empty());
    }
}
