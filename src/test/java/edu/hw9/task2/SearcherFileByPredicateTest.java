package edu.hw9.task2;

import java.io.File;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SearcherFileByPredicateTest {
    @Test
    @DisplayName("Ввод некорректных значений")
    void enteringIncorrectValues() {

        assertThatThrownBy(() -> new SearcherFileByPredicate(new File("src/test/java/edu/hw9/task2/testData"), null)).isInstanceOf(
            IllegalArgumentException.class);

        assertThatThrownBy(() -> new SearcherFileByPredicate(null, file -> file.length() > 20000)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка поиска")
    void checkingSearch() {
        Searcher searcher = new Searcher();
        List<File> result = searcher.searchingFileByPredicate(20, ".txt", new File("src/test/java/edu/hw9/task2/testData"));

        assertThat(result.size()).isEqualTo(2);
    }
}
