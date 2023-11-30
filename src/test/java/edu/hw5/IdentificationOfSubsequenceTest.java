package edu.hw5;

import edu.hw5.task6.IdentificationOfSubsequence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IdentificationOfSubsequenceTest {
    @Test
    @DisplayName("Проверка правильной подпоследовательности")
    void checkingCorrectSubsequence() {
        assertThat(IdentificationOfSubsequence.parsingString("abc", "achfdbaabgacaabg")).isEqualTo(true);
        assertThat(IdentificationOfSubsequence.parsingString("123", "123")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка неправильной подпоследовательности")
    void checkingAnIncorrectSubsequence() {
        assertThat(IdentificationOfSubsequence.parsingString("abc", "achfdbaabgabaabg")).isEqualTo(false);
    }

    @Test
    @DisplayName("Ввод некорректных данных")
    void enteringIncorrectData() {
        assertThatThrownBy(() -> IdentificationOfSubsequence.parsingString("abc", null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> IdentificationOfSubsequence.parsingString("", "achfdbaabgabaabg")).isInstanceOf(IllegalArgumentException.class);
    }
}
