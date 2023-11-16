package edu.hw6;

import edu.hw6.Task2.FileCloner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileClonerTest {
    @BeforeAll static void createFile() {
        try {
            Files.createFile(Path.of("src", "test", "res", "testTask2.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Проверка записи и чтения файла")
    void checkingFileWritingAndReading() {
        FileCloner.cloneFile(Path.of("src", "test", "res", "testTask2.txt"));

        assertThat(Files.exists(Path.of("src", "test", "res", "testTask2 - копия.txt"))).isTrue();

        FileCloner.cloneFile(Path.of("src", "test", "res", "testTask2.txt"));

        assertThat(Files.exists(Path.of("src", "test", "res", "testTask2 - копия (2).txt"))).isTrue();
    }

    @Test
    @DisplayName("Передача некорректного путя")
    void sendingIncorrectPath() {
        assertThatThrownBy(() -> FileCloner.cloneFile(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> FileCloner.cloneFile(Path.of("aboba"))).isInstanceOf(IllegalArgumentException.class);
    }

    @AfterAll static void deleteFile(){
        try {
            Files.delete(Path.of("src", "test", "res", "testTask2.txt"));
            Files.delete(Path.of("src", "test", "res", "testTask2 - копия.txt"));
            Files.delete(Path.of("src", "test", "res", "testTask2 - копия (2).txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
