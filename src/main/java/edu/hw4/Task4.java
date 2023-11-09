package edu.hw4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task4 {
    final static int ONE_HUNDRED = 100;

    //Задача 1: Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
    public List<Animal> sortHeight(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }
        return animals.stream()
            .filter(Objects::nonNull)
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());
    }

    //Задача 2: Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
    public List<Animal> sortWeight(List<Animal> animals, int k) {
        if (animals == null || animals.size() < k || k < 0) {
            throw new IllegalArgumentException();
        }
        return animals.stream()
            .filter(Objects::nonNull)
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .collect(Collectors.toList());
    }

    //Задача 3: Сколько животных каждого вида -> Map<Animal.Type, Integer>
    public Map<Animal.Type, Integer> sortType(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                Animal::type, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }

    //Задача 4: У какого животного самое длинное имя -> Animal
    public Animal longName(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(animal -> animal != null && animal.name() != null)
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElse(null);
    }

    //Задача 5: Каких животных больше: самцов или самок -> Sex
    public Animal.Sex maxSex(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }
        long m = animals.stream()
            .filter(animal -> animal != null && animal.sex() == Animal.Sex.M)
            .count();
        long f = animals.stream()
            .filter(animal -> animal != null && animal.sex() == Animal.Sex.F)
            .count();

        return m > f ? Animal.Sex.M : Animal.Sex.F;
    }

    //Задача 6: Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
    public Map<Animal.Type, Animal> heaviestAnimals(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .collect(Collectors
                .toMap(
                    Animal::type,
                    Function.identity(),
                    BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
                ));
    }

    //Задача 7: K-е самое старое животное -> Animal
    public Animal oldestAnimal(List<Animal> animals, int k) {
        if (animals == null || animals.size() < k || k < 0) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .sorted(Comparator.comparing(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    //Задача 8: Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    public Optional<Animal> heaviestAnimalBelowK(List<Animal> animals, int k) {
        if (animals == null || k < 0) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(animal -> animal != null && animal.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    //Задача 9: Сколько в сумме лап у животных в списке -> Integer
    public Integer sumPaws(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .mapToInt(Animal::paws)
            .sum();
    }

    //Задача 10: Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public List<Animal> unsatisfiedPaws(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(animal -> animal != null && animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    //Задача 11: Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см -> List<Animal>
    public List<Animal> animalsCanBite(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(animal -> animal != null && animal.bites() && animal.height() > ONE_HUNDRED)
            .collect(Collectors.toList());
    }

    //Задача 12: Сколько в списке животных, вес которых превышает рост -> Integer
    public Integer weightExceedsHeight(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return (int) animals.stream()
            .filter(animal -> animal != null && animal.weight() > animal.height())
            .count();

    }

    //Задача 13: Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    public List<Animal> namesMoreThanTwoWords(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(animal -> animal != null
                && animal.name() != null
                && animal.name().split("\\s+").length > 2)
            .collect(Collectors.toList());

    }

    //Задача 14: Есть ли в списке собака ростом более k см -> Boolean
    public Boolean dogWithHeightMoreThanK(List<Animal> animals, int k) {
        if (animals == null || k < 0) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    //Задача 15: Найти суммарный вес животных каждого вида, которым от k до l лет -> Map<Animal.Type, Integer>
    public Map<Animal.Type, Integer> totalWeightAnimals(List<Animal> animals, int k, int l) {
        if (animals == null || k < 0 || l < 0) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(animal -> animal != null && animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));

    }

    //Задача 16: Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Animal>
    public List<Animal> fullSorting(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(animal -> animal != null
                && animal.sex() != null
                && animal.type() != null
                && animal.name() != null)
            .sorted(Comparator
                .comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());

    }

    //Задача 17: Правда ли, что пауки кусаются чаще, чем собаки -> Boolean (если данных для ответа недостаточно, вернуть false)
    public boolean spidersBitesMore(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        long spider = animals.stream()
            .filter(animal -> animal != null
                && animal.type() == Animal.Type.SPIDER
                && animal.bites())
            .count();
        long dog = animals.stream()
            .filter(animal -> animal != null
                && animal.type() == Animal.Type.DOG
                && animal.bites())
            .count();

        return spider > dog;
    }

    //Задача 18: Найти самую тяжелую рыбку в 2-х или более списках -> Animal
    public Animal heaviestFish(List<Animal>... animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return Arrays.stream(animals)
            .filter(Objects::nonNull)
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }

    //Задача 19: Животные, в записях о которых есть ошибки: вернуть имя и список ошибок -> Map<String, Set<ValidationError>>
    public Map<String, Set<ValidationError>> countErrors(List<Animal> animals) {
        return animals.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(
                Animal::name,
                Validator::validate
            ));
    }

    //Задача 20: Сделать результат предыдущего задания более читабельным: вернуть имя и названия полей с ошибками, объединенные в строку -> Map<String, String>
    public Map<String, String> countErrorsModified(List<Animal> animals) {
        return animals.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(
                Animal::name,
                animal -> Validator
                    .validate(animal)
                    .stream()
                    .map(Throwable::getMessage)
                    .sorted()
                    .collect(Collectors.joining(", "))
            ));
    }
}
