package edu.hw5;

import edu.hw5.task2.Friday13;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Friday13Test {
    @Test
    @DisplayName("Проверка неккореткного значения в counterFriday13")
    void checkingAnCorrectValuesCounterFriday13() {
        assertThatThrownBy(() -> Friday13.counterFriday13(-100)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка неккореткного значения в searchNextFriday13")
    void checkingAnCorrectValuesSearchNextFriday13() {
        assertThatThrownBy(() -> Friday13.searchNextFriday13(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка counterFriday13")
    void checkingCCounterFriday13() {
        assertThat(Friday13.counterFriday13(1925)).isEqualTo(List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        ));

        assertThat(Friday13.counterFriday13(2024)).isEqualTo(List.of(
            LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 12, 13)
        ));
    }

    @Test
    @DisplayName("Проверка searchNextFriday13")
    void checkingSearchNextFriday13() {
        assertThat(Friday13.searchNextFriday13(LocalDate.of(1925, 2, 13))).isEqualTo(LocalDate.of(1925, 3, 13));
        assertThat(Friday13.searchNextFriday13(LocalDate.of(2024, 9, 13))).isEqualTo(LocalDate.of(2024, 12, 13));
    }

}
