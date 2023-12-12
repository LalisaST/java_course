package edu.hw10.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RandomObjectGeneratorTest {
    @Test
    @DisplayName("Ввод некорректных данных")
    void enteringIncorrectData() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();

        assertThatThrownBy(() -> randomObjectGenerator.nextObject(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> randomObjectGenerator.nextObject(
            String.class,
            null
        )).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> randomObjectGenerator.nextObject(
            String.class,
            "create"
        )).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Ввод корректных данных")
    void checkingCorrectData() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();

        MyClass obgClass1 = randomObjectGenerator.nextObject(MyClass.class);
        MyClass obgClass2 = randomObjectGenerator.nextObject(MyClass.class, "create");
        MyRecord obgRecord = randomObjectGenerator.nextObject(MyRecord.class);

        assertThat(obgClass1).isNotNull();
        assertThat(obgClass2).isNotNull();
        assertThat(obgRecord).isNotNull();

        assertThat(obgRecord.intValue()).isBetween(0, 1);
        assertThat(obgRecord.doubleValue()).isGreaterThan(2);
        assertThat(obgRecord.stringValue()).isNotNull();
    }
}
