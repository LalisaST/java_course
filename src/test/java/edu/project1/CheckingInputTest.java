package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckingInputTest {
    @Test
    @DisplayName("Ввод пустой строки")
    void enteringEmptyString() {
        Session session = new Session("pizza", 5, 1);
        GuessResult result = session.guess("");
        boolean flag = result instanceof GuessResult.Typo;

        assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("Ввод пробела")
    void enteringSpace() {
        Session session = new Session("pizza", 5, 1);
        GuessResult result = session.guess(" ");
        boolean flag = result instanceof GuessResult.Typo;

        assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("Ввод 'null'")
    void enteringNull() {
        Session session = new Session("pizza", 5, 1);
        GuessResult result = session.guess(null);
        boolean flag = result instanceof GuessResult.Typo;

        assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("Ввод лишних символов")
    void enteringExtraCharacters() {
        Session session = new Session("pizza", 5, 1);
        GuessResult result = session.guess("asd");
        boolean flag = result instanceof GuessResult.Typo;

        assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("Ввод табуляции")
    void enteringTab() {
        Session session = new Session("pizza", 5, 1);
        GuessResult result = session.guess("\t");
        boolean flag = result instanceof GuessResult.Typo;

        assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("Ввод цифры")
    void enteringDigit() {
        Session session = new Session("pizza", 5, 1);
        GuessResult result = session.guess("1");
        boolean flag = result instanceof GuessResult.Typo;

        assertThat(flag).isEqualTo(true);
    }
}
