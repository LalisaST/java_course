package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class RandomObjectGenerator {
    private static final Random RANDOM = new Random();
    private static final int NUM_OF_LETTERS_IN_ALPHABET = 26;
    private static final String FLOAT = "float";
    private static final String DOUBLE = "double";
    private static final String INT = "int";
    private static final String LONG = "long";
    private static final String BOOLEAN = "boolean";
    private static final String CHAR = "char";
    private static final String CLASS_FLOAT = "java.lang.Float";
    private static final String CLASS_DOUBLE = "java.lang.Double";
    private static final String CLASS_INT = "java.lang.Integer";
    private static final String CLASS_LONG = "java.lang.Long";
    private static final String CLASS_BOOLEAN = "java.lang.Boolean";
    private static final String CLASS_CHAR = "java.lang.Char";
    private static final String CLASS_STRING = "java.lang.String";

    public <T> T nextObject(Class<T> clazz, String factoryMethod) {
        if (clazz == null) {
            throw new IllegalArgumentException();
        }

        try {
            Method[] methods = clazz.getMethods();
            Method method = Arrays.stream(methods)
                .filter(m -> m.getName().equals(factoryMethod))
                .findFirst()
                .orElse(null);

            if (method == null) {
                throw new IllegalArgumentException();
            }

            Parameter[] parameters = method.getParameters();

            if (parameters.length == 0) {
                //noinspection unchecked
                return (T) method.invoke(null);
            }

            Object[] arguments = getArguments(parameters);

            //noinspection unchecked
            return (T) method.invoke(null, arguments);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T nextObject(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException();
        }

        try {
            Constructor<?>[] constructors = clazz.getConstructors();
            Constructor<?> randomConstructor = constructors[RANDOM.nextInt(constructors.length)];
            Parameter[] parameters = randomConstructor.getParameters();

            if (parameters.length == 0) {

                //noinspection unchecked
                return (T) randomConstructor.newInstance();
            }

            Object[] arguments = getArguments(parameters);

            //noinspection unchecked
            return (T) randomConstructor.newInstance(arguments);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object[] getArguments(Parameter[] parameters) {
        Object[] arguments = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            arguments[i] = randomArg(parameters[i]);
        }

        return arguments;
    }

    private Object randomArg(Parameter parameter) {
        String min = null;
        String max = null;

        if (parameter.isAnnotationPresent(Min.class)) {
            min = parameter.getAnnotation(Min.class).value();
        }
        if (parameter.isAnnotationPresent(Max.class)) {
            max = parameter.getAnnotation(Max.class).value();
        }

        return randomValue(parameter, min, max);
    }

    @SuppressWarnings("ReturnCount")
    private Object randomValue(Parameter parameter, String min, String max) {
        Class<?> type = parameter.getType();
        String finalMin = min;
        String finalMax = max;

        if (finalMin == null) {
            finalMin = getMin(type);
        }

        if (finalMax == null) {
            finalMax = getMax(type);
        }

        switch (type.getName()) {
            case INT, CLASS_INT -> {
                return RANDOM.nextInt(Integer.parseInt(finalMin), Integer.parseInt(finalMax));
            }
            case DOUBLE, CLASS_DOUBLE -> {
                return RANDOM.nextDouble(Double.parseDouble(finalMin), Double.parseDouble(finalMax));
            }
            case FLOAT, CLASS_FLOAT -> {
                return RANDOM.nextFloat(Float.parseFloat(finalMin), Float.parseFloat(finalMax));
            }
            case LONG, CLASS_LONG -> {
                return RANDOM.nextLong(Long.parseLong(finalMin), Long.parseLong(finalMax));
            }
            case BOOLEAN, CLASS_BOOLEAN -> {
                return RANDOM.nextBoolean();
            }
            case CHAR, CLASS_CHAR -> {
                return (char) (RANDOM.nextInt(NUM_OF_LETTERS_IN_ALPHABET) + 'a');
            }
            case CLASS_STRING -> {
                return UUID.randomUUID().toString();
            }
            default -> {
                return null;
            }
        }
    }

    @SuppressWarnings("ReturnCount")
    private <T> String getMax(Class<T> type) {
        switch (type.getName()) {
            case INT, CLASS_INT -> {
                return String.valueOf(Integer.MAX_VALUE);
            }
            case DOUBLE, CLASS_DOUBLE -> {
                return String.valueOf(Double.MAX_VALUE);
            }
            case FLOAT, CLASS_FLOAT -> {
                return String.valueOf(Float.MAX_VALUE);
            }
            case LONG, CLASS_LONG -> {
                return String.valueOf(Long.MAX_VALUE);
            }
            default -> {
                return String.valueOf(0);
            }
        }
    }

    @SuppressWarnings("ReturnCount")
    private <T> String getMin(Class<T> type) {
        switch (type.getName()) {
            case INT, CLASS_INT -> {
                return String.valueOf(Integer.MIN_VALUE);
            }
            case DOUBLE, CLASS_DOUBLE -> {
                return String.valueOf(-Double.MAX_VALUE);
            }
            case FLOAT, CLASS_FLOAT -> {
                return String.valueOf(Float.MIN_VALUE);
            }
            case LONG, CLASS_LONG -> {
                return String.valueOf(Long.MIN_VALUE);
            }
            default -> {
                return String.valueOf(0);
            }
        }
    }
}



