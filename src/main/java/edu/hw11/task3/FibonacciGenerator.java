package edu.hw11.task3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;

public class FibonacciGenerator {
    private FibonacciGenerator() {}

    public static Class<?> generateFibonacciClass() {

        return new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciClass")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC).withParameters(int.class)
            .intercept(new Implementation.Simple(new FibonacciMethodImplementation()))
            .make()
            .load(FibonacciGenerator.class.getClassLoader())
            .getLoaded();
    }
}
