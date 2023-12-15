package edu.hw11;

import edu.hw11.task3.FibonacciGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.assertj.core.api.Assertions.assertThat;

public class FibonacciGeneratorTest {
    @Test
    @DisplayName("Проверка выполнения")
    void checkingExecution()
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> dynamicType = FibonacciGenerator.generateFibonacciClass();

        Object object = dynamicType.getDeclaredConstructor().newInstance();
        Method fibMethod = dynamicType.getDeclaredMethod("fib", int.class);

        assertThat(fibMethod.invoke(object,4)).isEqualTo(3L);
        assertThat(fibMethod.invoke(object,1)).isEqualTo(1L);
        assertThat(fibMethod.invoke(object,0)).isEqualTo(0L);
    }
}
