package edu.hw5.task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexForCharacters {
    static final Pattern PATTERN = Pattern.compile("[~!@#$%^&*|]");

    private RegexForCharacters() {}

    public static boolean parsingCharacters(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        Matcher matcher = PATTERN.matcher(password);

        return matcher.find();
    }
}
