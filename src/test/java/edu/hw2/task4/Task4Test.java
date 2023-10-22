package edu.hw2.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.task4.Utils.callingInfo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка Multiplication")
    void checkingMultiplication() {
        assertThat(callingInfo().className()).isEqualTo("edu.hw2.task4.Task4Test");

        assertThat(callingInfo().methodName()).isEqualTo("checkingMultiplication");
    }
}
