package edu.hw5.task3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FormatterDate {
    private static List<DateParserHandler> formatDateParsersList = new ArrayList<>();

    /*static {
        formatDateParsersList.add(new FormatDateParser("yyyy-MM-dd"));
        formatDateParsersList.add(new FormatDateParser("d/M/yyyy"));
        formatDateParsersList.add(new FormatDateParser("d/M/yy"));
        formatDateParsersList.add(new FormatDateParser("yyyy-MM-d"));
        formatDateParsersList.add(new DaysAgoParser());
        formatDateParsersList.add(new RecentDayParser());
    }*/

    private FormatterDate() {}

    public static Optional<LocalDate> parseDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Optional<LocalDate> optionalLocalDate = Optional.empty();

        for (DateParserHandler parserHandler : formatDateParsersList) {
            optionalLocalDate = parserHandler.parseDate(dateString);

            if (optionalLocalDate.isPresent()) {
                return optionalLocalDate;
            }
        }

        return optionalLocalDate;
    }

    public static void setNext(List<DateParserHandler> formatDateParsersList) {
        FormatterDate.formatDateParsersList = formatDateParsersList;
    }
}
