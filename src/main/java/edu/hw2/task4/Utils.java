package edu.hw2.task4;

public class Utils {
    private Utils() {
    }

    public static CallingInfo callingInfo() {
        try {
            throw new Throwable();
        } catch (Throwable e) {
            StackTraceElement[] stackTraceElements = e.getStackTrace();
            StackTraceElement stackTraceElement = stackTraceElements[1];

            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();

            return new CallingInfo(className, methodName);
        }
    }
}
