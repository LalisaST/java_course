package edu.hw11.task1;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteBuddyClass {

    private ByteBuddyClass() {}

    public static Object toStringHelloByteBuddy() {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(ByteBuddyClass.class.getClassLoader())
            .getLoaded();

        try {
            return dynamicType.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                 | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
