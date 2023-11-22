package edu.hw7;


import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase;
import edu.hw7.task3.SynchronizedPersonDatabase;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import edu.hw7.task3.SynchronizedPersonDatabaseRWL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SynchronizedPersonDatabaseAndRWLTest {
    private static SynchronizedPersonDatabase personService;

    @BeforeAll static void setUp() {
        personService = new SynchronizedPersonDatabase();
        personService.add(new Person(1, "John Doe", "123 Main St", "555-1234"));
        personService.add(new Person(2, "Jane Doe", "456 Oak St", "555-5678"));
        personService.add(new Person(3, "Bob Smith", "789 Pine St", "555-4321"));
        personService.add(new Person(4, "Alice Johnson", "101 Elm St", "555-8765"));
    }

    @Test
    @DisplayName("Тест findByName")
    void testFindByName() {
        List<Person> people = personService.findByName("John Doe");
        List<Person> result = List.of(new Person(1, "John Doe", "123 Main St", "555-1234"));

        assertThat(people).isEqualTo(result);
    }

    @Test
    @DisplayName("Тест findByAddress")
    void testFindByAddress() {
        List<Person> people = personService.findByAddress("456 Oak St");
        List<Person> result = List.of(new Person(2, "Jane Doe", "456 Oak St", "555-5678"));

        assertThat(people).isEqualTo(result);
    }

    @Test
    @DisplayName("Тест findByPhone")
    void testFindByPhone() {
        List<Person> people = personService.findByPhone("555-4321");
        List<Person> result = List.of(new Person(3, "Bob Smith", "789 Pine St", "555-4321"));

        assertThat(people).isEqualTo(result);
    }

    @Test
    @DisplayName("Ввод некорректных значений")
    void testDelete() {
        personService.delete(4);
        List<Person> people = personService.findByName("101 Elm St");
        assertThat(people).isEmpty();
    }

    @Test
    @DisplayName("Ввод некорректных значений")
    void enteringIncorrectValues() {
        SynchronizedPersonDatabase test = new SynchronizedPersonDatabase();
        assertThatThrownBy(() ->test.add(new Person(-1, "", null, " ")))
            .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> dataBase() {
        return Stream.of(
            Arguments.of(new SynchronizedPersonDatabase()),
            Arguments.of(new SynchronizedPersonDatabaseRWL())
        );
    }

    @ParameterizedTest
    @MethodSource("dataBase")
    @DisplayName("Проверка потокабезопасности")
    void threadSafetyCheck(PersonDatabase personDatabase) {

        int numberOfThreads = 4;
        int numberOfOperationsPerThread = 10000000;

        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < numberOfOperationsPerThread; j++) {
                        personDatabase.add(new Person(j, "Name" + j, "Address" + j, "555-" + j));

                        assertThat(personDatabase.findByName("Name" + j)).isNotEmpty();

                        personDatabase.delete(j);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }
}
