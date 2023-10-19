package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class CheckingProgressGameTest {
    @Test
    @DisplayName("Проверка победы")
    void checkVictory() {
        Session session = new Session("pizza", 5, 1);
        session.guess("p");
        session.guess("i");
        session.guess("z");

        GuessResult result = session.guess("a");
        boolean flag = result instanceof GuessResult.Win;

        assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка поражения")
    void checkDefeat() {
        Session session = new Session("pizza", 5, 4);
        GuessResult result = session.guess("b");
        boolean flag = result instanceof GuessResult.Defeat;

        assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка попадания")
    void checkHit() {
        Session session = new Session("pizza", 5, 1);
        GuessResult result = session.guess("i");
        boolean flag = result instanceof GuessResult.SuccessfulGuess;

        assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка промаха")
    void checkMiss() {
        Session session = new Session("pizza", 5, 1);
        GuessResult result = session.guess("b");
        boolean flag = result instanceof GuessResult.FailedGuess;

        assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка на капитуляцию")
    void checkGiveUp() {
        Session session = new Session("pizza", 5, 1);
        GuessResult result = session.guess("0");
        boolean flag = result instanceof GuessResult.Defeat;

        assertThat(flag).isEqualTo(true);
    }
}
