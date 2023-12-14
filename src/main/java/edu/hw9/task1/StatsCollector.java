package edu.hw9.task1;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {
    private static final int METRICS_COUNT = 4;
    private final ExecutorService executorService;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<String, StatsResult> stats = new ConcurrentHashMap<>();

    public StatsCollector() {
        this.executorService = Executors.newFixedThreadPool(METRICS_COUNT);
    }

    public void push(String metricName, double[] data) {
        if (data == null || metricName == null || metricName.isBlank()) {
            throw new IllegalArgumentException();
        }

        try {
            lock.writeLock().lock();

            Future<Double> sum = executorService.submit(() -> Calculation.calculateSum(data));
            Future<Double> avg = executorService.submit(() -> Calculation.calculateAverage(data));
            Future<Double> max = executorService.submit(() -> Calculation.findMax(data));
            Future<Double> min = executorService.submit(() -> Calculation.findMin(data));

            try {
                stats.put(
                    metricName,
                    new StatsResult(sum.get(), avg.get(), max.get(), min.get())
                );
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        } finally {
            lock.writeLock().unlock();
        }

    }

    public Set<Map.Entry<String, StatsResult>> stats() {

        try {
            lock.readLock().lock();

            return stats.entrySet();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void close() {
        executorService.shutdown();
    }

}
