package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Friday13 {
    private static final int NUMBER_OF_MONTHS = 12;
    private static final int DAY13 = 13;

    private Friday13() {}

    public static List<LocalDate> counterFriday13(int year) {
        if (year < 0) {
            throw new IllegalArgumentException();
        }

        List<LocalDate> dates = new ArrayList<>();

        for (int i = 1; i <= NUMBER_OF_MONTHS; i++) {
            LocalDate date = LocalDate.of(year, i, DAY13);

            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                dates.add(date);
            }
        }
        return dates;
    }

    public static LocalDate searchNextFriday13(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException();
        }

        LocalDate nextFriday = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        if (nextFriday.getDayOfMonth() == DAY13) {
            return nextFriday;
        }

        return searchNextFriday13(nextFriday);
    }

}
