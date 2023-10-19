package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Проверка на вложенность")
    void checkingNesting() {
        int[] array1 = new int[] {1,2,3,4};
        int[] array2 = new int[] {0,6};

        boolean result = Task3.isNestable(array1, array2);

        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка на не вложенность")
    void checkingNonNesting() {
        int[] array1 = new int[] {9, 9, 8};
        int[] array2 = new int[] {8,9};

        boolean result = Task3.isNestable(array1, array2);

        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Ввод пустого массива")
    void enteringEmptyArray() {
        int[] array1 = new int[] {9, 9, 8};
        int[] array2 = new int[] {};

        boolean result = Task3.isNestable(array1, array2);

        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Ввод null")
    void enteringNull() {
        int[] array1 = null;
        int[] array2 = new int[] {8,9};

        boolean result = Task3.isNestable(array1, array2);

        assertThat(result).isEqualTo(false);
    }
}
