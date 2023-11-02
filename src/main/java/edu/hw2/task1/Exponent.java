package edu.hw2.task1;

public record Exponent(Expr lexpr, double rexpr) implements Expr {
    @Override
    public double evaluate() {
        if (lexpr == null) {
            throw new IllegalArgumentException();
        }

        if (!(Double.isFinite(Math.pow(lexpr().evaluate(), rexpr)))) {
            throw new IllegalArgumentException();
        }
        return Math.pow(lexpr().evaluate(), rexpr);
    }
}
