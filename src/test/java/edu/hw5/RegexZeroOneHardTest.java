package edu.hw5;

import edu.hw5.task8.RegexZeroOneHard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RegexZeroOneHardTest {
    @Test
    @DisplayName("Проверка isOddLength")
    void checkingIsOddLength() {
        assertThat(RegexZeroOneHard.isOddLength("1")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isOddLength("10101")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка неправильных строк isOddLength")
    void checkingWrongStringsIsOddLength() {
        assertThat(RegexZeroOneHard.isOddLength("00")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isOddLength("1111")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isOddLength("")).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка isStarts0WithAnOddLengthOr1WithAnEvenLength")
    void checkingIsFirstLastCharactersEqual() {
        assertThat(RegexZeroOneHard.isStarts0WithAnOddLengthOr1WithAnEvenLength("1010")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isStarts0WithAnOddLengthOr1WithAnEvenLength("11")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isStarts0WithAnOddLengthOr1WithAnEvenLength("0")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isStarts0WithAnOddLengthOr1WithAnEvenLength("000")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка неправильных строк isStarts0WithAnOddLengthOr1WithAnEvenLength")
    void checkingWrongStringsIsStarts0WithAnOddLengthOr1WithAnEvenLength() {
        assertThat(RegexZeroOneHard.isStarts0WithAnOddLengthOr1WithAnEvenLength("00")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isStarts0WithAnOddLengthOr1WithAnEvenLength("111")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isStarts0WithAnOddLengthOr1WithAnEvenLength("")).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка isNumberOf0MultipleOf3")
    void checkingIsNumberOf0MultipleOf3() {
        assertThat(RegexZeroOneHard.isNumberOf0MultipleOf3("000000")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isNumberOf0MultipleOf3("10000000000001")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isNumberOf0MultipleOf3("000")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isNumberOf0MultipleOf3("0001")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка неправильных строк isNumberOf0MultipleOf3")
    void checkingWrongStringsIsNumberOf0MultipleOf3() {
        assertThat(RegexZeroOneHard.isNumberOf0MultipleOf3("00")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isNumberOf0MultipleOf3("1010010")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isNumberOf0MultipleOf3("")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка isAnyStringOther11Or111")
    void checkingIsAnyStringOther11Or111() {
        assertThat(RegexZeroOneHard.isAnyStringOther11Or111("1")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isAnyStringOther11Or111("1111")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isAnyStringOther11Or111("10")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isAnyStringOther11Or111("000")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка неправильных строк isAnyStringOther11Or111")
    void checkingWrongStringsIsAnyStringOther11Or111() {
        assertThat(RegexZeroOneHard.isAnyStringOther11Or111("11")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isAnyStringOther11Or111("111")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isAnyStringOther11Or111("")).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка isEachOddCharacterEqual1")
    void checkingIsEachOddCharacterEqual1() {
        assertThat(RegexZeroOneHard.isEachOddCharacterEqual1("111")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isEachOddCharacterEqual1("10")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isEachOddCharacterEqual1("10")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isEachOddCharacterEqual1("10101")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isEachOddCharacterEqual1("1")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка неправильных строк isEachOddCharacterEqual1")
    void checkingWrongStringsIsEachOddCharacterEqual1() {
        assertThat(RegexZeroOneHard.isEachOddCharacterEqual1("110")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isEachOddCharacterEqual1("010")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isEachOddCharacterEqual1("101010110")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isEachOddCharacterEqual1("")).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка isContainsLeasTwo0AndMoreOne1")
    void checkingIsContainsLeasTwo0AndMoreOne1() {
        assertThat(RegexZeroOneHard.isContainsLeasTwo0AndMoreOne1("100")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isContainsLeasTwo0AndMoreOne1("010")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isContainsLeasTwo0AndMoreOne1("001")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isContainsLeasTwo0AndMoreOne1("0010000")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isContainsLeasTwo0AndMoreOne1("00")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка неправильных строк isContainsLeasTwo0AndMoreOne1")
    void checkingWrongStringsIsContainsLeasTwo0AndMoreOne1() {
        assertThat(RegexZeroOneHard.isContainsLeasTwo0AndMoreOne1("10")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isContainsLeasTwo0AndMoreOne1("00101")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isContainsLeasTwo0AndMoreOne1("")).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка isNoConsecutive1")
    void checkingIsoConsecutive1() {
        assertThat(RegexZeroOneHard.isNoConsecutive1("1001010100001")).isEqualTo(true);
        assertThat(RegexZeroOneHard.isNoConsecutive1("010")).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка неправильных строк isNoConsecutive1")
    void checkingWrongStringsIsNoConsecutive1() {
        assertThat(RegexZeroOneHard.isNoConsecutive1("1111")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isNoConsecutive1("00100011")).isEqualTo(false);
        assertThat(RegexZeroOneHard.isNoConsecutive1("")).isEqualTo(false);
    }

    @Test
    @DisplayName("Ввод некорректных данных")
    void enteringIncorrectData() {
        assertThatThrownBy(() -> RegexZeroOneHard.isOddLength(null)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> RegexZeroOneHard.isStarts0WithAnOddLengthOr1WithAnEvenLength(null)).isInstanceOf(
            IllegalArgumentException.class);

        assertThatThrownBy(() -> RegexZeroOneHard.isNumberOf0MultipleOf3(null)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> RegexZeroOneHard.isAnyStringOther11Or111(null)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> RegexZeroOneHard.isEachOddCharacterEqual1(null)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> RegexZeroOneHard.isContainsLeasTwo0AndMoreOne1(null)).isInstanceOf(
            IllegalArgumentException.class);

        assertThatThrownBy(() -> RegexZeroOneHard.isNoConsecutive1(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
