package edu.project3;

public class Main {

    private Main() {
    }

    public static void main(String[] args) {

        // String logPath = getOptionValue(args, "--path");
        String logPath = "src/main/resources/abobaLog.txt";

        String from = getOptionValue(args, "--from");
        String to = getOptionValue(args, "--to");

        String format = getOptionValue(args, "--format", "markdown");

        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.analyzeLogs(logPath, from, to, "adoc");
    }

    private static String getOptionValue(String[] args, String option, String defaultValue) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals(option)) {
                return args[i + 1];
            }
        }
        return defaultValue;
    }

    private static String getOptionValue(String[] args, String option) {
        return getOptionValue(args, option, null);
    }
}
