package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HackerNewsTest {
    @Test
    @DisplayName("Проверка работы hackerNewsTopStories")
    void checkingWorkHackerNewsTopStories() {
        assertThat(HackerNews.hackerNewsTopStories()).isNotEmpty();
    }

    @Test
    @DisplayName("Проверка работы news")
    void checkingWorkHackerNews() {
        assertThat(HackerNews.news(37570037)).isEqualTo("JDK 21 Release Notes");
    }

    @Test
    @DisplayName("Проверка ввода 0 в news")
    void checkingInput0InNews() {
        assertThat(HackerNews.news(0)).isNull();
    }

}
