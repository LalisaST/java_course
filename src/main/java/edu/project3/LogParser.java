package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LogParser {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;

    private LogParser() {
    }

    public static LogRecord parse(String logLine) {
        if (logLine == null) {
            throw new IllegalArgumentException();
        }

        DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z").withLocale(Locale.ENGLISH);
        Pattern pattern = Pattern
            .compile("^(.+) - (.+) \\[(.+)] \"(.+)\" (\\d+) (\\d+) \"(.+)\" \"(.+)\"$");
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.matches()) {
            String remoteAddr = matcher.group(ONE);
            String remoteUser = matcher.group(TWO);
            OffsetDateTime timeLocal = OffsetDateTime.parse(matcher.group(THREE), dateTimeFormatter);
            String request = matcher.group(FOUR);
            int status = Integer.parseInt(matcher.group(FIVE));
            long bodyBytesSent = Long.parseLong(matcher.group(SIX));
            String httpReferer = matcher.group(SEVEN);
            String httpUserAgent = matcher.group(EIGHT);

            return new LogRecord(remoteAddr, remoteUser, timeLocal, request, status, bodyBytesSent,
                httpReferer, httpUserAgent
            );
        } else {
            throw new IllegalArgumentException("Некорректный формат строки лога: " + logLine);
        }
    }
}
