package edu.hw3;


import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task8Test {
    @Test
    @DisplayName("Ввод корректных значений")
    void enteringCorrectValues() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1,2,3));
        assertThat(backwardIterator.next()).isEqualTo(3);
        assertThat(backwardIterator.next()).isEqualTo(2);
        assertThat(backwardIterator.next()).isEqualTo(1);
    }

    @Test
    @DisplayName("Ввод null")
    void enteringNull() {
        assertThatThrownBy(() -> {
            BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ввод пустой коллекции")
    void enteringEmptyCollection() {
        assertThatThrownBy(() -> {
            BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Выход за границу")
    void goingAbroad() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1));
        backwardIterator.next();
        assertThatThrownBy(backwardIterator::next).isInstanceOf(NoSuchElementException.class);
    }
}
