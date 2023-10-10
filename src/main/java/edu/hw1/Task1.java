package edu.hw1;

public class Task1 {
    static final int MAX = 60;
    static final int MAX_MINUTE = 35791393;

    private Task1() {

    }

    public static int minutesToSeconds(String time) {

        if (time == null || !time.contains(":") || time.length() < 3) {
            return -1;
        }

        String[] t = time.split(":");
        int result;
        int mm;
        int ss;

        try {
            mm = Integer.parseInt(t[0]);
            ss = Integer.parseInt(t[1]);
        } catch (NumberFormatException e) {
            return -1;
        }

        if (ss >= MAX || ss < 0 || mm < 0 || mm > MAX_MINUTE) {
            return -1;
        }

        result = mm * MAX + ss;
        return result;
    }
}
