package edu.hw2.task1;

public sealed interface Expr {

    double evaluate();

    public record Constant(double evaluate) implements Expr {
    }

    public record Negate(Expr value) implements Expr {
        @Override
        public double evaluate() {
            if (value == null) {
                throw new IllegalArgumentException();
            }
            return -value.evaluate();
        }
    }

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

    public record Addition(Expr lexpr, Expr rexpr) implements Expr {
        @Override
        public double evaluate() {
            if (lexpr == null || rexpr == null) {
                throw new IllegalArgumentException();
            }

            return lexpr.evaluate() + rexpr.evaluate();
        }
    }

    public record Multiplication(Expr lexpr, Expr rexpr) implements Expr {
        @Override
        public double evaluate() {
            if (lexpr == null || rexpr == null) {
                throw new IllegalArgumentException();
            }

            return lexpr.evaluate() * rexpr.evaluate();
        }
    }
}
