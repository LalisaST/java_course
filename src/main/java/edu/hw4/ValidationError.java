package edu.hw4;

import java.util.Objects;

public class ValidationError extends Error {
    public ValidationError(String s) {
        super(s);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ValidationError error)) {
            return false;
        }

        return this.getMessage().equals(error.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getMessage());
    }
}

