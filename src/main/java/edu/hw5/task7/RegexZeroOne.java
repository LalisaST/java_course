package edu.hw5.task7;

public class RegexZeroOne {

    private RegexZeroOne() {}

    public static boolean isLeast3Characters(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        return str.matches("^[01]{2}0[01]*$");
    }

    public static boolean isFirstLastCharactersEqual(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        return str.matches("^([01])([01]*\\1)?$");
    }

    public static boolean isLengthNotLess1AndNotMoreThan3(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        return str.matches("^[01]{1,3}$");
    }
}
