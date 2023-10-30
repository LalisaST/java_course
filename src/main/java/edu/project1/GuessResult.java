package edu.project1;

import org.jetbrains.annotations.NotNull;

sealed interface GuessResult {

    char[] state();

    @NotNull String message();

    record Defeat(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }

    record Win(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }

    record SuccessfulGuess(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }

    record FailedGuess(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }

    record Typo(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }
}

