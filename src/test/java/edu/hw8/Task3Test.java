package edu.hw8;

import edu.hw8.task3.Hacker;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task3Test {
    @Test
    @DisplayName("Передача некорректного аргумента")
    void enteringIncorrectArguments() {
        Hacker hacker = new Hacker();

        assertThatThrownBy(() -> hacker.hackingPasswords(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка passwordSearch")
    void checkingPasswordSearch() {
        Hacker hacker = new Hacker();
        Map<String, String> result = hacker.hackingPasswords(1);

        assertThat(result).isEqualTo(Map.of(
            "a.v.petrov",
            "1234",
            "v.v.belov",
            "qwer",
            "a.s.ivanov",
            "Qq1w",
            "k.p.maslov",
            "vdaD"
        ));
    }

    @Test
    @DisplayName("Проверка passwordSearchMultithreaded")
    void checkingPasswordSearchMultithreaded() throws InterruptedException {
        Hacker hacker = new Hacker();
        Map<String, String> result = hacker.hackingPasswords(5);

        assertThat(result).isEqualTo(Map.of(
            "a.v.petrov",
            "1234",
            "v.v.belov",
            "qwer",
            "a.s.ivanov",
            "Qq1w",
            "k.p.maslov",
            "vdaD"
        ));
    }
}
