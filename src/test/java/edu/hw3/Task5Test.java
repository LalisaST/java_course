package edu.hw3;

import edu.hw3.task5.ParseContacts;
import edu.hw3.task5.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task5Test {
    static Stream<Arguments> provideIncorrectValues() {
        return Stream.of(
            Arguments.of(new String[] {"John Locke", "", "David Hume", "Rene Descartes"}, "ASC"),
            Arguments.of(new String[] {"John Locke", null, "David Hume", "Rene Descartes"}, "ASC"),
            Arguments.of(new String[] {"John Locke", "Thomas Aquinas Hume", "David Hume", "Rene Descartes"}, "ASC"),
            Arguments.of(new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, null),
            Arguments.of(new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "")
        );
    }

    @DisplayName("Проверка некорректных значений")
    @ParameterizedTest
    @MethodSource("provideIncorrectValues")
    void checkingIncorrectValues(String[] str, String sorting) {
        assertThatThrownBy(() -> {
            ParseContacts parseContacts = new ParseContacts();
            parseContacts.parseContacts(str, sorting);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> provideCorrectValues() {
        return Stream.of(
            Arguments.of(new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC",
                new Person[] {new Person("Thomas", "Aquinas"), new Person("Rene", "Descartes"),
                    new Person("David", "Hume"), new Person("John", "Locke")}
            ),
            Arguments.of(new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC",
                new Person[] {new Person("Carl", "Gauss"), new Person("Leonhard", "Euler"), new Person("Paul", "Erdos")}
            ),
            Arguments.of(new String[] {"Paul", "Leonhard", "Carl Gauss"}, "ASC",
                new Person[] {new Person("Carl", "Gauss"), new Person("Leonhard", null), new Person("Paul", null)}
            ),
            Arguments.of(new String[] {"Paul Erdos", "Carl"}, "ASC",
                new Person[] {new Person("Carl", null), new Person("Paul", "Erdos")}
            ),
            Arguments.of(new String[] {}, "ASC", new Person[] {}),
            Arguments.of(null, "ASC", new Person[] {})
        );
    }

    @DisplayName("Проверка корректных значений")
    @ParameterizedTest
    @MethodSource("provideCorrectValues")
    void checkingCorrectValues(String[] str, String sorting, Person[] result) {
        ParseContacts parseContacts = new ParseContacts();
        assertThat(parseContacts.parseContacts(str, sorting)).isEqualTo(result);
    }
}
