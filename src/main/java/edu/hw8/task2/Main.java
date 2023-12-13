package edu.hw8.task2;

import java.util.concurrent.CountDownLatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private static long calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }

    private Main() {}

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);

        try (ThreadPool threadPool = FixedThreadPool.create(4)) {
            threadPool.start();

            for (int i = 0; i < 10; i++) {
                int finalI = i;
                threadPool.execute(() -> {
                    long result = calculateFibonacci(finalI);
                    LOGGER.info("Fibonacci(" + finalI + ") = " + result);
                    latch.countDown();
                });
            }
            latch.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
