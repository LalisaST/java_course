package edu.hw5;

import edu.hw5.task5.RegexForLicensePlates;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RegexForLicensePlatesTest {
    @Test
    @DisplayName("Проверка правильного номера")
    void checkingCorrectNumber() {
        assertThat(RegexForLicensePlates.parsingNumber("А123ВЕ777")).isEqualTo(true);
        assertThat(RegexForLicensePlates.parsingNumber("О777ОО177")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка неправильного номера")
    void checkingWrongNumber() {
        assertThat(RegexForLicensePlates.parsingNumber("123АВЕ777")).isEqualTo(false);
        assertThat(RegexForLicensePlates.parsingNumber("А123ВГ77")).isEqualTo(false);
        assertThat(RegexForLicensePlates.parsingNumber("А123ВЕ7777")).isEqualTo(false);
        assertThat(RegexForLicensePlates.parsingNumber("А123 ВЕ7777")).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка некорректного номера")
    void checkingIncorrectNumber() {
        assertThatThrownBy(() -> RegexForLicensePlates.parsingNumber(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> RegexForLicensePlates.parsingNumber(" ")).isInstanceOf(IllegalArgumentException.class);
    }
}
