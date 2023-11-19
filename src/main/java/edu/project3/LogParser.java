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
    private static final int NINE = 9;
    private static final int TEN = 10;

    private LogParser() {
    }

    public static LogRecord parse(String logLine) {
        if (logLine == null) {
            throw new IllegalArgumentException();
        }

        DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z").withLocale(Locale.ENGLISH);
        Pattern pattern = Pattern
            .compile("^(.+) - (.+) \\[(.+)] \"(.+) (.+) (.+)\" (\\d+) (\\d+) \"(.+)\" \"(.+)\"$");
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.matches()) {
            String remoteAddr = matcher.group(ONE);
            String remoteUser = matcher.group(TWO);
            OffsetDateTime timeLocal = OffsetDateTime.parse(matcher.group(THREE), dateTimeFormatter);
            String method = matcher.group(FOUR);
            String request = matcher.group(FIVE);
            String version = matcher.group(SIX);
            int status = Integer.parseInt(matcher.group(SEVEN));
            long bodyBytesSent = Long.parseLong(matcher.group(EIGHT));
            String httpReferer = matcher.group(NINE);
            String httpUserAgent = matcher.group(TEN);

            return new LogRecord(remoteAddr, remoteUser, timeLocal, method, request, version, status, bodyBytesSent,
                httpReferer, httpUserAgent
            );
        } else {
            throw new IllegalArgumentException("Некорректный формат строки лога: " + logLine);
        }
    }
}
