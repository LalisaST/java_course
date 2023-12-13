package edu.hw6;

import edu.hw6.Task3.Filter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FilterTest {
    @BeforeAll static void createFile() {
        try {
            Files.createFile(Path.of("src", "test", "res", "testTask3.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Проверка работы фильтров")
    void checkingOperationFilters() throws IOException {

        DirectoryStream.Filter<Path> filter = Filter.readable()
            .and(Filter.largerThan(-1))
            .and(Filter.globMatches("**.gif"))
            .and(Filter.regexContains("-"))
            .and(Filter.magicNumber(0x47));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src", "test", "res"), filter)) {
            entries.forEach(path -> assertThat(path).isEqualTo(Path.of(
                "src",
                "test",
                "res",
                "forest-gump-wave.gif"
            )));
        }

    }

    @Test
    @DisplayName("Ввод некорректных аргументов")
    void enteringIncorrectArguments() {
        assertThatThrownBy(() -> Filter.globMatches(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Filter.regexContains(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Filter.readable().and(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @AfterAll static void deleteFile() {
        try {
            Files.delete(Path.of("src", "test", "res", "testTask3.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
