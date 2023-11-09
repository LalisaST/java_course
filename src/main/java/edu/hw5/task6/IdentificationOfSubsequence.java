package edu.hw5.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentificationOfSubsequence {

    private IdentificationOfSubsequence() {}

    public static boolean parsingString(String s, String t) {
        if (s == null || s.trim().isEmpty() || t == null || t.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : chars) {
            stringBuilder.append("[^").append(c).append("]*+").append(c);
        }

        Pattern pattern = Pattern.compile(stringBuilder.toString());
        Matcher matcher = pattern.matcher(t);

        return matcher.find();
    }
}
