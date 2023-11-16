package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DiskMapTest {
    @Test
    @DisplayName("Проверка записи и чтения файла")
    void checkingFileWritingAndReading() {
        DiskMap diskMap = new DiskMap("aboba.txt");
        diskMap.put("dog", "black");
        diskMap.put("cat", "brown");
        diskMap.put("parrot", "blue");
        diskMap.put("pig", "pink");

        DiskMap diskMapResult = new DiskMap("aboba.txt");
        assertThat(diskMap).containsExactlyInAnyOrderEntriesOf(diskMapResult);
    }

    @Test
    @DisplayName("Проверка некорректных имен")
    void checkingInvalidNames() {
        assertThatThrownBy(() -> new DiskMap("")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new DiskMap(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @AfterAll static void deleteFile(){
        try {
            Files.delete(Path.of("aboba.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
