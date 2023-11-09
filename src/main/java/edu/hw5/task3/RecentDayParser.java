package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecentDayParser implements DateParserHandler {
    @Override
    public Optional<LocalDate> parseDate(String dateString) {
        Pattern pattern = Pattern.compile("^(yesterday|tomorrow|today)$");
        LocalDate dateNow = LocalDate.now();
        LocalDate result;

        Matcher matcher = pattern.matcher(dateString);
        if (matcher.find()) {

            result = switch (matcher.group(0)) {
                case "yesterday" -> dateNow.minusDays(1);
                case "tomorrow" -> dateNow.plusDays(1);
                case "today" -> dateNow;
                default -> throw new IllegalArgumentException();
            };

            return Optional.of(result);
        }
        return Optional.empty();
    }
}
