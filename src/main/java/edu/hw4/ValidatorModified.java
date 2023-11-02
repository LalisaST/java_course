package edu.hw4;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatorModified {
    private ValidatorModified() {
    }

    public static String validate(Animal animal) {
        Set<ValidationError> setError = new HashSet<>();

        if (animal.name() == null || animal.name().isEmpty()) {
            setError.add(new ValidationError("Name: The name is set incorrectly"));
        }
        if (animal.age() < 0) {
            setError.add(new ValidationError("Age: The age is set incorrectly"));
        }
        if (animal.height() < 0) {
            setError.add(new ValidationError("Height: Growth is incorrectly set"));
        }
        if (animal.weight() < 0) {
            setError.add(new ValidationError("Weight: The weight is set incorrectly"));
        }
        if (animal.type() == null) {
            setError.add(new ValidationError("Type: The type is set incorrectly"));
        }
        if (animal.sex() == null) {
            setError.add(new ValidationError("Sex: The sex is set incorrectly"));
        }

        return setError.stream()
            .map(Throwable::getMessage).sorted()
            .collect(Collectors.joining(", "));
    }
}
