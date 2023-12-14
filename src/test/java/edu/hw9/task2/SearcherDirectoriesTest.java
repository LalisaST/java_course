package edu.hw9.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SearcherDirectoriesTest {
    @Test
    @DisplayName("Ввод некорректных значений")
    void enteringIncorrectValues() {
        assertThatThrownBy(() -> new SearcherDirectories(0 , new File("src/test/java/edu/hw9/task2/testData"))).isInstanceOf(
            IllegalArgumentException.class);

        assertThatThrownBy(() -> new SearcherDirectories(1 , null)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка поиска")
    void checkingSearch() {
        Searcher searcher = new Searcher();
        List<File> result = searcher.searchingDirectories(2, new File("src/test/java/edu/hw9/task2/testData"));

        assertThat(result.size()).isEqualTo(1);
    }
}
