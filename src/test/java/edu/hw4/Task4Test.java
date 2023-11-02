package edu.hw4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task4Test {
    @Test
    @DisplayName("Ввод null в sortHeight")
    void enteringNullSortHeight() {
        Task4 task4 = new Task4();
        assertThatThrownBy(() -> task4.sortHeight(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка sortHeight")
    void checkingSortHeight() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 3, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 0, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 1, 2, 0, false),
            new Animal("Spinner", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true)
        );

        List<Animal> animalsSort = Arrays.asList(
            new Animal("Spinner", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 1, 2, 0, false),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 0, false),
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 3, 30, 15, true)
        );
        assertThat(task4.sortHeight(animals)).isEqualTo(animalsSort);
    }

    @Test
    @DisplayName("Проверка sortWeight")
    void checkingSortWeight() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 3, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 20, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 1, 2, 40, false),
            new Animal("Spinner", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true)
        );

        List<Animal> animalsSort = Arrays.asList(
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 1, 2, 40, false),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 20, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 3, 30, 15, true)
        );
        assertThat(task4.sortWeight(animals, 3)).isEqualTo(animalsSort);
    }

    static Stream<Arguments> provideIncorrectValuesSortWeight() {
        return Stream.of(
            Arguments.of(null, 3),
            Arguments.of(Arrays.asList(
                new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 25, 4, false),
                new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 3, 30, 15, true),
                new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 20, false)
            ), 4),
            Arguments.of(Arrays.asList(
                new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 25, 4, false),
                new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 3, 30, 15, true),
                new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 20, false)
            ), -1)
        );
    }

    @DisplayName("Проверка некорректных значений sortWeight")
    @ParameterizedTest
    @MethodSource("provideIncorrectValuesSortWeight")
    void checkingIncorrectValues(List<Animal> animals, int k) {
        assertThatThrownBy(() -> {
            Task4 task4 = new Task4();
            task4.sortWeight(animals, k);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ввод null в sortType")
    void enteringNullSortType() {
        Task4 task4 = new Task4();
        assertThatThrownBy(() -> task4.sortType(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка sortType")
    void checkingSortType() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 3, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 20, false),
            new Animal("Goldy", Animal.Type.CAT, Animal.Sex.F, 1, 2, 40, false),
            new Animal("Spinner", Animal.Type.CAT, Animal.Sex.M, 1, 1, 1, true)
        );

        assertThat(task4.sortType(animals)).isEqualTo(Map.of(
            Animal.Type.CAT, 3,
            Animal.Type.DOG, 1,
            Animal.Type.BIRD, 1));
    }

    @Test
    @DisplayName("Проверка searchLongName")
    void checkingSearchLongName() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 3, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 0, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 1, 2, 0, false),
            new Animal("Spinner", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true)
        );

        assertThat(task4.longName(animals)).isEqualTo(new Animal(
            "Spinner",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            1, 1, 0,
            true
        ));
    }

    @Test
    @DisplayName("Проверка searchMaxSex")
    void checkingSearchMaxSex() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 3, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 0, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 1, 2, 0, false),
            new Animal("Spinner", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true)
        );

        assertThat(task4.maxSex(animals)).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Проверка searchHeaviestAnimals")
    void checkingSearchHeaviestAnimals() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 25, 5, false),
            new Animal("Buddy", Animal.Type.CAT, Animal.Sex.M, 3, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 7, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 1, 2, 1, false),
            new Animal("Spinner", Animal.Type.FISH, Animal.Sex.M, 1, 1, 3, true)
        );

        Map<Animal.Type, Animal> map = Map.of(
            Animal.Type.CAT, new Animal("Buddy", Animal.Type.CAT, Animal.Sex.M, 3, 30, 15, true),
            Animal.Type.FISH, new Animal("Spinner", Animal.Type.FISH, Animal.Sex.M, 1, 1, 3, true),
            Animal.Type.BIRD, new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 7, false)
        );

        assertThat(task4.heaviestAnimals(animals)).isEqualTo(map);
    }

    @Test
    @DisplayName("Проверка searchOldestAnimal")
    void checkingSearchOldestAnimal() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false),
            new Animal("Buddy", Animal.Type.CAT, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 4, 5, 7, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 2, 2, 1, false),
            new Animal("Spinner", Animal.Type.FISH, Animal.Sex.M, 1, 1, 3, true)
        );

        assertThat(task4.oldestAnimal(animals, 3)).isEqualTo(new Animal(
            "Fluffy",
            Animal.Type.CAT,
            Animal.Sex.F,
            3, 25, 5,
            false
        ));
    }

    @Test
    @DisplayName("Проверка SearchHeaviestAnimalBelowK")
    void checkingSearchHeaviestAnimalBelowK() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false),
            new Animal("Buddy", Animal.Type.CAT, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 4, 5, 7, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 2, 2, 1, false),
            new Animal("Spinner", Animal.Type.FISH, Animal.Sex.M, 1, 1, 3, true)
        );

        assertThat(task4.heaviestAnimalBelowK(animals, 10).orElse(null)).isEqualTo(new Animal(
            "Tweety",
            Animal.Type.BIRD,
            Animal.Sex.M,
            4, 5, 7,
            false
        ));
    }

    @Test
    @DisplayName("Проверка sumPaws")
    void checkingSumPaws() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 3, 25, 5, false),
            new Animal("Buddy", Animal.Type.CAT, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 4, 5, 7, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 2, 2, 1, false),
            new Animal("Spinner", Animal.Type.FISH, Animal.Sex.M, 1, 1, 3, true)
        );

        assertThat(task4.sumPaws(animals)).isEqualTo(10);
    }

    @Test
    @DisplayName("Проверка unsatisfiedPaws")
    void checkingUnsatisfiedPaws() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 4, 25, 5, false),
            new Animal("Buddy", Animal.Type.CAT, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 2, 5, 7, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 2, 2, 1, false),
            new Animal("Spinner", Animal.Type.BIRD, Animal.Sex.M, 8, 5, 7, false)
        );

        assertThat(task4.unsatisfiedPaws(animals)).isEqualTo(List.of(
            new Animal("Buddy", Animal.Type.CAT, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 2, 2, 1, false),
            new Animal("Spinner", Animal.Type.BIRD, Animal.Sex.M, 8, 5, 7, false)
            ));
    }

    @Test
    @DisplayName("Проверка animalsCanBite")
    void checkingAnimalsCanBite() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 4, 25, 5, false),
            new Animal("Buddy", Animal.Type.CAT, Animal.Sex.M, 5, 150, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 2, 5, 7, true),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 2, 400, 1, true),
            new Animal("Spinner", Animal.Type.BIRD, Animal.Sex.M, 8, 5, 7, false)
        );

        assertThat(task4.animalsCanBite(animals)).isEqualTo(List.of(
            new Animal("Buddy", Animal.Type.CAT, Animal.Sex.M, 5, 150, 15, true),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 2, 400, 1, true)
        ));
    }

    @Test
    @DisplayName("Проверка weightExceedsHeight")
    void checkingWeightExceedsHeight() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 4, 25, 5, false),
            new Animal("Buddy", Animal.Type.CAT, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 2, 5, 7, true),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 2, 2, 1, true),
            new Animal("Spinner", Animal.Type.BIRD, Animal.Sex.M, 8, 3, 10, false)
        );

        assertThat(task4.weightExceedsHeight(animals)).isEqualTo(2);
    }

    @Test
    @DisplayName("Проверка namesMoreThanTwoWords")
    void checkingNamesMoreThanTwoWords() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy Buddy Tweety", Animal.Type.CAT, Animal.Sex.F, 4, 25, 5, false),
            new Animal("Buddy Spinner", Animal.Type.CAT, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.M, 2, 5, 7, true),
            new Animal("Goldy Tweety Spinner", Animal.Type.FISH, Animal.Sex.F, 2, 2, 1, true),
            new Animal("Spinner", Animal.Type.BIRD, Animal.Sex.M, 8, 3, 10, false)
        );

        assertThat(task4.namesMoreThanTwoWords(animals)).isEqualTo(List.of(
            new Animal("Fluffy Buddy Tweety", Animal.Type.CAT, Animal.Sex.F, 4, 25, 5, false),
            new Animal("Goldy Tweety Spinner", Animal.Type.FISH, Animal.Sex.F, 2, 2, 1, true)
        ));
    }

    @Test
    @DisplayName("Проверка dogWithHeightMoreThanK на true")
    void checkingDogWithHeightMoreThanKTrue() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 3, 30, 15, true),
            new Animal("Tweety", Animal.Type.DOG, Animal.Sex.M, 1, 40, 0, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 1, 2, 0, false),
            new Animal("Spinner", Animal.Type.DOG, Animal.Sex.M, 1, 10, 0, true)
        );

        assertThat(task4.dogWithHeightMoreThanK(animals, 10)).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка dogWithHeightMoreThanK на false")
    void checkingDogWithHeightMoreThanKFalse() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 3, 30, 15, true),
            new Animal("Tweety", Animal.Type.DOG, Animal.Sex.M, 1, 40, 0, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 1, 2, 0, false),
            new Animal("Spinner", Animal.Type.DOG, Animal.Sex.M, 1, 10, 0, true)
        );

        assertThat(task4.dogWithHeightMoreThanK(animals, 100)).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка totalWeightAnimals")
    void checkingTotalWeightAnimals() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 12, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Tweety", Animal.Type.DOG, Animal.Sex.M, 9, 40, 1, false),
            new Animal("Goldy", Animal.Type.FISH, Animal.Sex.F, 1, 2, 2, false),
            new Animal("Spinner", Animal.Type.CAT, Animal.Sex.M, 1, 10, 5, true)
        );

        assertThat(task4.totalWeightAnimals(animals, 1, 10)).isEqualTo(Map.of(
            Animal.Type.DOG, 16,
            Animal.Type.FISH, 2,
            Animal.Type.CAT, 5));
    }

    @Test
    @DisplayName("Проверка fullSorting")
    void checkingFullSorting() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 12, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Goldy", Animal.Type.DOG, Animal.Sex.M, 9, 40, 1, false),
            new Animal("Tweety", Animal.Type.FISH, Animal.Sex.F, 1, 2, 2, false),
            new Animal("Spinner", Animal.Type.CAT, Animal.Sex.M, 1, 10, 5, true),
            new Animal("Buffy", Animal.Type.CAT, Animal.Sex.M, 2, 15, 2, true)
        );

        assertThat(task4.fullSorting(animals)).isEqualTo(List.of(
            new Animal("Buffy", Animal.Type.CAT, Animal.Sex.M, 2, 15, 2, true),
            new Animal("Spinner", Animal.Type.CAT, Animal.Sex.M, 1, 10, 5, true),
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 12, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Goldy", Animal.Type.DOG, Animal.Sex.M, 9, 40, 1, false),
            new Animal("Tweety", Animal.Type.FISH, Animal.Sex.F, 1, 2, 2, false)
        ));
    }

    @Test
    @DisplayName("Проверка heaviestFish")
    void checkingHeaviestFish() {
        Task4 task4 = new Task4();
        List<Animal> animals1 = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 12, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Goldy", Animal.Type.DOG, Animal.Sex.M, 9, 40, 1, false),
            new Animal("Tweety", Animal.Type.FISH, Animal.Sex.F, 1, 2, 2, false),
            new Animal("Spinner", Animal.Type.FISH, Animal.Sex.M, 1, 10, 5, true)
        );

        List<Animal> animals2 = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 12, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Goldy", Animal.Type.DOG, Animal.Sex.M, 9, 40, 1, false),
            new Animal("Tweety", Animal.Type.FISH, Animal.Sex.F, 1, 2, 10, false),
            new Animal("Spinner", Animal.Type.CAT, Animal.Sex.M, 1, 10, 5, true)
        );

        assertThat(task4.heaviestFish(animals1, animals2)).isEqualTo(new Animal("Tweety", Animal.Type.FISH, Animal.Sex.F, 1, 2, 10, false));
    }

    @Test
    @DisplayName("Проверка spidersBitesMore на true")
    void checkingSpidersBitesMoreTrue() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 12, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, 5, 30, 15, true),
            new Animal("Goldy", Animal.Type.DOG, Animal.Sex.M, 9, 40, 1, false),
            new Animal("Tweety", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 2, false),
            new Animal("Spinner", Animal.Type.SPIDER, Animal.Sex.M, 1, 10, 5, true)
        );

        assertThat(task4.spidersBitesMore(animals)).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка spidersBitesMore на false")
    void checkingSpidersBitesMoreFalse() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 12, 25, 4, false),
            new Animal("Tweety", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 2, false),
            new Animal("Spinner", Animal.Type.SPIDER, Animal.Sex.M, 1, 10, 5, true)
        );

        assertThat(task4.spidersBitesMore(animals)).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка countErrors")
    void checkingCountErrors() {
        Task4 task4 = new Task4();
        List<Animal> animals = Arrays.asList(
            new Animal("A", null, null, 12, 25, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, -10, -20, 15, true),
            new Animal("Goldy", Animal.Type.DOG, Animal.Sex.M, 9, 40, -5, false),
            new Animal("", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 2, false),
            new Animal(null, Animal.Type.SPIDER, Animal.Sex.M, 1, 10, 5, true)
        );

        Map<String, Set<ValidationError>> mapResult = task4.countErrors(animals);

        assertThat(mapResult.get("A")).contains(new ValidationError("The type is set incorrectly"), new ValidationError("The sex is set incorrectly"));
        assertThat(mapResult.get("Buddy")).contains(new ValidationError("The age is set incorrectly"), new ValidationError("Growth is incorrectly set"));
        assertThat(mapResult.get("Goldy")).contains(new ValidationError("The weight is set incorrectly"));
        assertThat(mapResult.get("")).contains(new ValidationError("The name is set incorrectly"));
        assertThat(mapResult.get(null)).contains(new ValidationError("The name is set incorrectly"));
    }

    @Test
    @DisplayName("Проверка countErrorsModified")
    void checkingCountErrorsModified() {
        Task4 task4 = new Task4();

        List<Animal> animals = Arrays.asList(
            new Animal("A", Animal.Type.DOG, Animal.Sex.M, -12, -1, 4, false),
            new Animal("Buddy", Animal.Type.DOG, Animal.Sex.M, -10, -20, 15, true),
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 9, 40, -5, false),
            new Animal("Goldy", null, Animal.Sex.F, 1, 2, 2, false),
            new Animal(null, Animal.Type.SPIDER, null, 1, 10, 5, true)
        );

        Map<String, String> mapResult = task4.countErrorsModified(animals);

        assertThat(mapResult.get("A")).isEqualTo("Age: The age is set incorrectly, Height: Growth is incorrectly set");
        assertThat(mapResult.get("Buddy")).isEqualTo("Age: The age is set incorrectly, Height: Growth is incorrectly set");
        assertThat(mapResult.get("")).isEqualTo("Name: The name is set incorrectly, Weight: The weight is set incorrectly");
        assertThat(mapResult.get("Goldy")).isEqualTo("Type: The type is set incorrectly");
        assertThat(mapResult.get(null)).isEqualTo("Name: The name is set incorrectly, Sex: The sex is set incorrectly");
    }
}
