package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AverageTime {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    private AverageTime() {}

    public static String averageTimeSearcher(List<String> dataTime) {
        if (dataTime == null) {
            throw new IllegalArgumentException();
        }

        Duration totalDuration = Duration.ZERO;

        for (String timeStr : dataTime) {
            if (timeStr == null) {
                throw new IllegalArgumentException();
            }

            String[] time = timeStr.split(" - ");

            LocalDateTime startTime = LocalDateTime.parse(time[0], DTF);
            LocalDateTime endTime = LocalDateTime.parse(time[1], DTF);

            totalDuration = totalDuration.plus(Duration.between(startTime, endTime));
        }

        totalDuration = totalDuration.dividedBy(dataTime.size());

        return totalDuration.toHoursPart() + "ч " + totalDuration.toMinutesPart() + "м";
    }
}
