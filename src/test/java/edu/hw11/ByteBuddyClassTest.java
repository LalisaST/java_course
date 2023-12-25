package edu.hw11;

import edu.hw11.task1.ByteBuddyClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ByteBuddyClassTest {
    @Test
    @DisplayName("Проверка выполнения")
    void checkingExecution() {
        assertThat(ByteBuddyClass.toStringHelloByteBuddy().toString()).isEqualTo("Hello, ByteBuddy!");
    }
}
