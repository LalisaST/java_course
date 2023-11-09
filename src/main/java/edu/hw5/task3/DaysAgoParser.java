package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaysAgoParser implements DateParserHandler {
    @Override
    public Optional<LocalDate> parseDate(String dateString) {
        Pattern pattern = Pattern.compile("(\\d+) day(s)? ago");
        LocalDate dateNow = LocalDate.now();

        Matcher matcher = pattern.matcher(dateString);
        if (matcher.find()) {
            int days = Integer.parseInt(matcher.group(1));
            LocalDate result = dateNow.minusDays(days);
            return Optional.of(result);
        }
        return Optional.empty();
    }
}
