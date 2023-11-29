package edu.hw8.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        Hacker hacker = new Hacker();

        long startTime1 = System.nanoTime();
        hacker.hackingPasswords(1);
        long endTime1 = System.nanoTime();
        long duration1 = (endTime1 - startTime1) / 1_000_000;

        LOGGER.info("Threads: " + 1 + ", Time: " + duration1 + " ms");

        for (int numOfThreads = 5; numOfThreads <= 15; numOfThreads++) {
            long startTime = System.nanoTime();
            hacker.hackingPasswords(numOfThreads);
            long endTime = System.nanoTime();

            long duration = (endTime - startTime) / 1_000_000;

            double speedup = (double) duration1 / duration;

            LOGGER.info("Threads: " + numOfThreads + ", Time: " + duration + " ms, Speedup: " + speedup);
        }
    }
}
