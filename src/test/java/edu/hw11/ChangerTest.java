package edu.hw11;


import edu.hw11.task2.ArithmeticUtils;
import edu.hw11.task2.Changer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ChangerTest {
    @Test
    @DisplayName("Проверка выполнения")
    void checkingExecution() {
        Changer.changeMethod();

        assertThat(ArithmeticUtils.sum(4,3)).isEqualTo(12);
    }
}
