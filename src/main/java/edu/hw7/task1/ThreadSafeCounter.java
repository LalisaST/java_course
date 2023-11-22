package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter {
    private static final int INCREMENTS = 1000;

    private ThreadSafeCounter() {}

    public static int count(int sizeThread) {
        if (sizeThread <= 1) {
            throw new IllegalArgumentException();
        }

        AtomicInteger counter = new AtomicInteger(0);
        Thread[] thread = new Thread[sizeThread];

        for (int i = 0; i < sizeThread; i++) {
            thread[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) {
                    counter.incrementAndGet();
                }
            });

            thread[i].start();
        }

        for (int i = 0; i < sizeThread; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return counter.get();
    }
}

