package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class PiCalculator {
    private static final double RADIUS = 1.0;
    private static final double CALCULATING_COEFFICIENT = 4.0;

    private PiCalculator() {}

    public static double calculateMultiThread(int totalCount, int numOfThreads) {
        if (totalCount < 1 || numOfThreads < 2) {
            throw new IllegalArgumentException();
        }

        int iterationsPerThread = totalCount / numOfThreads;
        AtomicInteger circleCount = new AtomicInteger();
        Thread[] threads = new Thread[numOfThreads];

        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new Thread(() -> {
                int circleCountInThread = getCircleCount(iterationsPerThread);
                circleCount.addAndGet(circleCountInThread);
            });

            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return CALCULATING_COEFFICIENT * ((double) circleCount.get() / totalCount);
    }

    public static double calculateSingleThread(int totalCount) {
        if (totalCount < 1) {
            throw new IllegalArgumentException();
        }

        int circleCount = getCircleCount(totalCount);
        return CALCULATING_COEFFICIENT * ((double) circleCount / totalCount);
    }

    private static int getCircleCount(int numOfIterations) {
        int circleCount = 0;

        for (int i = 0; i < numOfIterations; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if (Math.pow(x, 2) + Math.pow(y, 2) <= RADIUS) {
                circleCount++;
            }
        }

        return circleCount;
    }
}
