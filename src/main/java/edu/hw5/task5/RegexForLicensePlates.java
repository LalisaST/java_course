package edu.hw5.task5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexForLicensePlates {
    static final String CHARS = "АВЕКМНОРСТУХ";
    static final Pattern PATTERN = Pattern.compile("^[" + CHARS + "]\\d{3}[" + CHARS + "]{2}\\d{3}$");

    private RegexForLicensePlates() {}

    public static boolean parsingNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        Matcher matcher = PATTERN.matcher(number);

        return matcher.find();
    }
}
