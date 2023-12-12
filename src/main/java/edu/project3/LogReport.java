package edu.project3;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogReport {
    private static final int MAX_SIZE_RESULT = 3;

    private final Supplier<Stream<LogRecord>> logRecords;

    public LogReport(List<LogRecord> logRecords) {
        if (logRecords == null) {
            throw new IllegalArgumentException();
        }

        this.logRecords = logRecords::stream;
    }

    public long getTotalRequests() {
        return logRecords.get().count();
    }

    public Map<String, Long> getTopRequestedResources() {
        return logRecords.get()
            .collect(Collectors.groupingBy(LogRecord::request, Collectors.counting()))
            .entrySet().stream()
            .limit(MAX_SIZE_RESULT)
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<String, Long> getTopRequestedMethods() {
        return logRecords.get()
            .collect(Collectors.groupingBy(LogRecord::method, Collectors.counting()))
            .entrySet().stream()
            .limit(MAX_SIZE_RESULT)
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<String, Long> getTopRequestedVersions() {
        return logRecords.get()
            .collect(Collectors.groupingBy(LogRecord::version, Collectors.counting()))
            .entrySet().stream()
            .limit(MAX_SIZE_RESULT)
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<Integer, Long> getTopResponseStatusCodes() {
        return logRecords.get()
            .collect(Collectors.groupingBy(LogRecord::status, Collectors.counting()))
            .entrySet().stream()
            .limit(MAX_SIZE_RESULT)
            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public double getAverageResponseSize() {
        return logRecords.get()
            .mapToLong(LogRecord::bodyBytesSent)
            .average()
            .orElse(0);
    }

    public OffsetDateTime getStartDate() {
        return logRecords.get()
            .map(LogRecord::timeLocal)
            .min(Comparator.naturalOrder())
            .orElse(null);
    }

    public OffsetDateTime getEndDate() {
        return logRecords.get()
            .map(LogRecord::timeLocal)
            .max(Comparator.naturalOrder())
            .orElse(null);
    }
}
