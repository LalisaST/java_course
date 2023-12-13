package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {}

    public static void main(String[] args) {
        int totalCount = 10000000;

        long startTime1 = System.nanoTime();
        PiCalculator.calculateSingleThread(totalCount);
        long endTime1 = System.nanoTime();
        long duration1 = (endTime1 - startTime1) / 1_000_000;

        for (int numOfThreads = 2; numOfThreads <= 12; numOfThreads++) {
            long startTime = System.nanoTime();
            PiCalculator.calculateMultiThread(totalCount, numOfThreads);
            long endTime = System.nanoTime();

            long duration = (endTime - startTime) / 1_000_000;

            double speedup = (double) duration1 / duration;

            LOGGER.info("Threads: " + numOfThreads + ", Time: " + duration + " ms, Speedup: " + speedup);
        }

        int[] simulations = {10000000, 100000000, 1000000000};

        for (int simulationCount : simulations) {
            double singleThreadResult = PiCalculator.calculateSingleThread(simulationCount);

            double error = (Math.PI - singleThreadResult) / Math.PI * 100;

            LOGGER.info("Simulations: " + simulationCount + ", Error: " + error);
        }
    }
}
