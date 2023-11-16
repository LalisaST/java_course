package edu.hw6;


import edu.hw6.Task4.OutputStreamsComposition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OutputStreamsCompositionTest {
    @BeforeAll static void createFile() {
        try {
            Files.createFile(Path.of("src", "test", "res", "testTask4.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Проверка работы compose")
    void checkingWorkCompose() {
        OutputStreamsComposition.compose(Path.of("src", "test", "res", "testTask4.txt"));
        try {
            assertThat(Files.readString(Path.of("src", "test", "res", "testTask4.txt")))
                .isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @DisplayName("Ввод некорректных аргументов")
    void enteringIncorrectArguments() throws IOException {
        assertThatThrownBy(() -> OutputStreamsComposition.compose(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @AfterAll static void deleteFile(){
        try {
            Files.delete(Path.of("src", "test", "res", "testTask4.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
