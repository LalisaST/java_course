package edu.hw3;

import edu.hw3.task1.Atbash;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task1Test {
    @Test
    @DisplayName("Ввод null")
    void enteringNull() {

        assertThatThrownBy(() -> {
            String str = null;
            Atbash.atbash(str);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> provideStrings() {
        return Stream.of(
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"),
            Arguments.of("абоба! 1! 2!", "абоба! 1! 2!"),
            Arguments.of("", ""),
            Arguments.of("", "")
        );
    }

    @DisplayName("Проверка с допустимыми строками")
    @ParameterizedTest
    @MethodSource("provideStrings")
    void validStringsTest(String input, String expected) {
        assertThat(Atbash.atbash(input)).isEqualTo(expected);
    }
}
