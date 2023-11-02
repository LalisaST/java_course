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

    public List<Animal> sortHeight(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }
        return animals.stream()
            .filter(Objects::nonNull)
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());
    }

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

    public Map<Animal.Type, Integer> sortType(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                Animal::type, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }

    public Animal longName(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.name() != null)
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElse(null);
    }

    public Animal.Sex maxSex(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }
        long m = animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.sex() == Animal.Sex.M)
            .count();
        long f = animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.sex() == Animal.Sex.F)
            .count();

        return m > f ? Animal.Sex.M : Animal.Sex.F;
    }

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

    public Optional<Animal> heaviestAnimalBelowK(List<Animal> animals, int k) {
        if (animals == null || k < 0) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    public Integer sumPaws(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .mapToInt(Animal::paws).sum();
    }

    public List<Animal> unsatisfiedPaws(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    public List<Animal> animalsCanBite(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.bites() && animal.height() > ONE_HUNDRED)
            .collect(Collectors.toList());
    }

    public Integer weightExceedsHeight(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return (int) animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.weight() > animal.height())
            .count();

    }

    public List<Animal> namesMoreThanTwoWords(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.name() != null)
            .filter(animal -> animal.name().split(" ").length > 2)
            .collect(Collectors.toList());

    }

    public Boolean dogWithHeightMoreThanK(List<Animal> animals, int k) {
        if (animals == null || k < 0) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public Map<Animal.Type, Integer> totalWeightAnimals(List<Animal> animals, int k, int l) {
        if (animals == null || k < 0 || l < 0) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));

    }

    public List<Animal> fullSorting(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.sex() != null)
            .filter(animal -> animal.type() != null)
            .filter(animal -> animal.name() != null)
            .sorted(Comparator
                .comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());

    }

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

    public boolean spidersBitesMore(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException();
        }

        long spider = animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();
        long dog = animals.stream()
            .filter(Objects::nonNull)
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();

        return spider > dog;
    }

    public Map<String, Set<ValidationError>> countErrors(List<Animal> animals) {
        return animals.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(
                Animal::name,
                Validator::validate
            ));
    }

    public Map<String, String> countErrorsModified(List<Animal> animals) {
        return animals.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(
                Animal::name,
                ValidatorModified::validate
            ));
    }
}
