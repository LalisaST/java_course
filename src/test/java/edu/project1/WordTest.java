package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordTest {
    @Test
    @DisplayName("Проверка угадоваемого слова = пробел")
    void checkingGuessedWordSpace() {
        assertThatThrownBy(() -> new Session(" ", 5, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка угадываемого слова = null")
    void checkGuessedWordNull() {
        assertThatThrownBy(() -> new Session(null, 5, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Инициализации библиотеки пустым массивом")
    void initLibraryEmptyArray() {
        assertThatThrownBy(() -> {
            String[] food = new String[] {};
            FoodDictionary foodDictionary = new FoodDictionary();
            foodDictionary.setFood(food);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Инициализации библиотеки недопустимым массивом")
    void initLibraryInvalidArray() {
        assertThatThrownBy(() -> {
            FoodDictionary foodDictionary = new FoodDictionary();
            foodDictionary.setFood(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Инициализации библиотеки корректным массивом")
    void initLibraryValidArray() {
        String[] food = new String[] {"cake", "cookie", "pizza", "soup", "cheese"};
        FoodDictionary foodDictionary = new FoodDictionary();
        foodDictionary.setFood(food);
        String str = foodDictionary.randomWord();
        assertThat(str).isNotNull();
    }
}
