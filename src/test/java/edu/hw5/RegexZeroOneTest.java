package edu.hw5;

import edu.hw5.task7.RegexZeroOne;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RegexZeroOneTest {
    @Test
    @DisplayName("Проверка isLeast3Characters")
    void checkingIsLeast3Characters() {
        assertThat(RegexZeroOne.isLeast3Characters("0001")).isEqualTo(true);
        assertThat(RegexZeroOne.isLeast3Characters("11011")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка isFirstLastCharactersEqual")
    void checkingIsFirstLastCharactersEqual() {
        assertThat(RegexZeroOne.isFirstLastCharactersEqual("101")).isEqualTo(true);
        assertThat(RegexZeroOne.isFirstLastCharactersEqual("000000000111111000000000")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка isLengthNotLess1AndNotMoreThan3")
    void checkingIsLengthNotLess1AndNotMoreThan3() {
        assertThat(RegexZeroOne.isLengthNotLess1AndNotMoreThan3("011")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка неправильных строк")
    void checkingCorrectNumber() {
        assertThat(RegexZeroOne.isLeast3Characters("00")).isEqualTo(false);
        assertThat(RegexZeroOne.isLeast3Characters("00100")).isEqualTo(false);

        assertThat(RegexZeroOne.isFirstLastCharactersEqual("11010")).isEqualTo(false);
        assertThat(RegexZeroOne.isFirstLastCharactersEqual("00001")).isEqualTo(false);

        assertThat(RegexZeroOne.isLengthNotLess1AndNotMoreThan3("")).isEqualTo(false);
        assertThat(RegexZeroOne.isLengthNotLess1AndNotMoreThan3("000000")).isEqualTo(false);
    }

    @Test
    @DisplayName("Ввод некорректных строк")
    void enteringIncorrectLines() {
        assertThatThrownBy(() -> RegexZeroOne.isLeast3Characters(null)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> RegexZeroOne.isFirstLastCharactersEqual(null)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> RegexZeroOne.isLengthNotLess1AndNotMoreThan3(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
