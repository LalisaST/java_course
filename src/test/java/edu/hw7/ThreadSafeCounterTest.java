package edu.hw7;


import edu.hw7.task1.ThreadSafeCounter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

public class ThreadSafeCounterTest {
    @Test
    @DisplayName("Проверка потокобезопасности")
    void threadSafetyCheck() {
        assertThat(ThreadSafeCounter.count(5)).isEqualTo(5000);
    }

    @Test
    @DisplayName("Ввод некорректного количества потоков")
    void enteringIncorrectNumberThreads() {
        assertThatThrownBy(() -> ThreadSafeCounter.count(1)).isInstanceOf(IllegalArgumentException.class);
    }
}
