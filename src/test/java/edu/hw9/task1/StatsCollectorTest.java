package edu.hw9.task1;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StatsCollectorTest {
    @Test
    @DisplayName("Ввод некорректных значений")
    void enteringIncorrectValuesInFunction() {
        StatsCollector statsCollector = new StatsCollector();

        assertThatThrownBy(() -> statsCollector.push(null, new double[1])).isInstanceOf(
            IllegalArgumentException.class);

        assertThatThrownBy(() -> statsCollector.push("aboba", null)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка результата")
    void checkingResult() {
        StatsCollector statsCollector = new StatsCollector();
        double[] arr = {2.0, 9.0, 3.0, 5.0, 6.0};

        statsCollector.push("aboba", arr);
        Set<Map.Entry<String, StatsResult>> result = statsCollector.stats();

        StatsResult nextResult = result.iterator().next().getValue();

        assertThat(result.iterator().next().getKey()).isEqualTo("aboba");
        assertThat(nextResult.sum()).isEqualTo(25.0);
        assertThat(nextResult.avg()).isEqualTo(5.0);
        assertThat(nextResult.max()).isEqualTo(9.0);
        assertThat(nextResult.min()).isEqualTo(2.0);

        statsCollector.close();
    }

    @Test
    @DisplayName("Проверка потокобезопасности")
    void threadSafetyCheck() throws InterruptedException {
        StatsCollector statsCollector = new StatsCollector();
        int numberOfThreads = 4;

        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Random random = ThreadLocalRandom.current();

        for (int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    double[] arr = new double[5];

                    for (int j = 0; j < arr.length; j++) {
                        arr[j] = random.nextDouble(1, 10);
                    }

                    statsCollector.push(String.valueOf(finalI), arr);
                    StatsResult result = statsCollector.stats()
                        .stream()
                        .filter(entry -> entry.getKey().equals(String.valueOf(finalI)))
                        .findAny()
                        .orElse(null)
                        .getValue();

                    assertThat(result.sum()).isEqualTo(finalI);
                    assertThat(result.avg()).isEqualTo(finalI);
                    assertThat(result.max()).isEqualTo(finalI);
                    assertThat(result.min()).isEqualTo(finalI);

                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();
        statsCollector.close();
    }
}
