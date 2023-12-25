package edu.hw10.task2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy<T> implements InvocationHandler {
    private final Object target;
    private final Map<Method, Map<Integer, T>> cache;


    private CacheProxy(Object target) {
        this.target = target;
        this.cache = new HashMap<>();

    }

    public static <T> T create(T target, Class<?> targetClass) {
        if (target == null || targetClass == null) {
            throw new IllegalArgumentException();
        }

        //noinspection unchecked
        return (T) Proxy.newProxyInstance(
            targetClass.getClassLoader(),
            targetClass.getInterfaces(),
            new CacheProxy<>(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.isAnnotationPresent(Cache.class)) {
            int number = (int) args[0];

            if (!cache.containsKey(method)) {
                cache.put(method, new HashMap<>());
            }

            if (cache.get(method).containsKey(number)) {
                return cache.get(method).get(number);
            } else {
                T result;

                try {
                    //noinspection unchecked
                    result = (T) method.invoke(target, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

                cache.get(method).put(number, result);
                if (method.getAnnotation(Cache.class).persist()) {
                    persistResult(method, number, result);
                }
                return result;
            }
        } else {
            try {
                return method.invoke(target, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void persistResult(Method method, int number, T result) {
        try {
            String fileName = method.getName() + "_" + number + ".cache";

            File file = new File(fileName);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(result);
            }

            file.deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
