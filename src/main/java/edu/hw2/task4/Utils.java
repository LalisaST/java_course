package edu.hw2.task4;

public class Utils {
    private Utils() {
    }

    public static CallingInfo callingInfo(Throwable throwable) {
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[0];

        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();

        return new CallingInfo(className, methodName);
    }
}
