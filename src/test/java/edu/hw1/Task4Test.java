package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Ввод пустой строки")
    void enteringEmptyString() {
        String str = "";

        String result = Task4.fixString(str);

        assertThat(result).isEqualTo("");
    }

    @Test
    @DisplayName("Ввод корректной строки 1")
    void checkingCorrectingString1() {
        String str = "hTsii  s aimex dpus rtni.g";

        String result = Task4.fixString(str);

        assertThat(result).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Ввод корректной строки 2")
    void checkingCorrectingString2() {
        String str = "badce";

        String result = Task4.fixString(str);

        assertThat(result).isEqualTo("abcde");
    }
}
