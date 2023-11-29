package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public interface DateParserHandler {
    Optional<LocalDate> parseDate(String dateString);
}
