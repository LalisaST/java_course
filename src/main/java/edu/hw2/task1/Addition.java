package edu.hw2.task1;

public record Addition(Expr lexpr, Expr rexpr) implements Expr {
    @Override
    public double evaluate() {
        if (lexpr == null || rexpr == null) {
            throw new IllegalArgumentException();
        }

        return lexpr.evaluate() + rexpr.evaluate();
    }
}
