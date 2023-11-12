package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class Validator {
    private Validator() {
    }

    public static Set<ValidationError> validate(Animal animal) {
        Set<ValidationError> setError = new HashSet<>();

        if (animal.name() == null || animal.name().isEmpty()) {
            setError.add(new ValidationError("The name is set incorrectly"));
        }
        if (animal.age() < 0) {
            setError.add(new ValidationError("The age is set incorrectly"));
        }
        if (animal.height() < 0) {
            setError.add(new ValidationError("Growth is incorrectly set"));
        }
        if (animal.weight() < 0) {
            setError.add(new ValidationError("The weight is set incorrectly"));
        }
        if (animal.type() == null) {
            setError.add(new ValidationError("The type is set incorrectly"));
        }
        if (animal.sex() == null) {
            setError.add(new ValidationError("The sex is set incorrectly"));
        }

        return setError;
    }
}
