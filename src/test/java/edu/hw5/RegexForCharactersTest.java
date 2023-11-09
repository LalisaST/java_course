package edu.hw5;

import edu.hw5.task4.RegexForCharacters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RegexForCharactersTest {
    @Test
    @DisplayName("Проверка правильного пароля")
    void checkingCorrectPassword() {
        assertThat(RegexForCharacters.parsingCharacters("132455!")).isEqualTo(true);
        assertThat(RegexForCharacters.parsingCharacters("$132455!")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка неправильного пароля")
    void checkingWrongPassword() {
        assertThat(RegexForCharacters.parsingCharacters("132455")).isEqualTo(false);
        assertThat(RegexForCharacters.parsingCharacters("132 455")).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка некорректного пароля")
    void checkingIncorrectPassword() {
        assertThatThrownBy(() -> RegexForCharacters.parsingCharacters(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> RegexForCharacters.parsingCharacters(" ")).isInstanceOf(IllegalArgumentException.class);
    }
}
