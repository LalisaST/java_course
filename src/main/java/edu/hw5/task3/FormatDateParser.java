package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class FormatDateParser implements DateParserHandler {
    private final DateTimeFormatter formatter;

    public FormatDateParser(String format) {
        this.formatter = DateTimeFormatter.ofPattern(format);
    }

    @Override
    public Optional<LocalDate> parseDate(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString, formatter);
            return Optional.of(date);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
