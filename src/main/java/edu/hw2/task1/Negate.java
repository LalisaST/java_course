package edu.hw2.task1;

public record Negate(Expr value) implements Expr {
    @Override
    public double evaluate() {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return -value.evaluate();
    }
}
