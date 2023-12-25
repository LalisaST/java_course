package edu.hw10.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CacheProxyTest {
    @Test
    @DisplayName("Ввод некорректных данных")
    void enteringIncorrectData() {
        assertThatThrownBy(() -> CacheProxy.create(null, Calculator.class)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> CacheProxy.create(new Calculator(), null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ввод корректных данных")
    void checkingCorrectData() {
        FibCalculator fibCalculator = CacheProxy.create(new Calculator(), Calculator.class);
        long result = fibCalculator.fibNoCache(1);
        long result2 = fibCalculator.fib(0);

        String fileName = "fib_0.cache";
        File file = new File(fileName);

        assertThat(result).isEqualTo(1);
        assertThat(result2).isEqualTo(0);
        assertThat(file.exists()).isTrue();
    }
}
