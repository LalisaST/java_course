package edu.hw8;

import edu.hw8.task2.FixedThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task2Test {
    @Test
    @DisplayName("Передача некорректных аргументов")
    void enteringIncorrectArguments() {
        assertThatThrownBy(() -> FixedThreadPool.create(0)).isInstanceOf(IllegalArgumentException.class);

        FixedThreadPool fixedThreadPool1 = FixedThreadPool.create(1);
        assertThatThrownBy(() -> fixedThreadPool1.execute(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка потокобезопасности")
    void threadSafetyCheck() throws InterruptedException {
        FixedThreadPool threadPool = FixedThreadPool.create(4);
        AtomicInteger counter = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(8);

        threadPool.start();
        for (int i = 0; i < 8; i++) {
            threadPool.execute(() -> {
                counter.incrementAndGet();
                latch.countDown();
            });
        }

        threadPool.close();
        latch.await();

        assertThat(counter.get()).isEqualTo(8);
    }
}
