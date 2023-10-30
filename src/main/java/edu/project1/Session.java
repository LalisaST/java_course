package edu.project1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    Session(String answer, int maxAttempts, int attempts) {
        if (answer == null || answer.length() < 2) {
            throw new IllegalArgumentException();
        }

        this.answer = answer;
        this.maxAttempts = maxAttempts;
        this.attempts = attempts;

        this.userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '*');
    }

    @NotNull GuessResult guess(String guess) {
        if (guess == null || guess.length() != 1 || guess.equals(" ") || guess.equals("\t")
            || (!Character.isLetter(guess.charAt(0)) && guess.charAt(0) != '0')) {
            return new GuessResult.Typo(userAnswer, attempts, maxAttempts, "> Typo!");
        }

        char guessChar = guess.charAt(0);

        if (guessChar == '0') {
            return new GuessResult.Defeat(userAnswer, attempts, maxAttempts, "> You give up!");
        }

        if (hit(guessChar)) {
            return guessHit();
        }

        attempts++;
        return guessMiss();
    }

    private GuessResult guessHit() {
        if (answer.equals(new String(userAnswer))) {
            return new GuessResult.Win(userAnswer, attempts, maxAttempts, "> You won!");
        }
        return new GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts, "> Hit!");
    }

    private GuessResult guessMiss() {
        String missed = "> Missed, mistake " + attempts + " out of " + maxAttempts + ".";
        if (attempts == maxAttempts) {
            return new GuessResult.Defeat(userAnswer, attempts, maxAttempts, missed + " You lost!");
        }
        return new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts, missed);
    }

    private boolean hit(char guess) {
        char[] ans = answer.toCharArray();
        boolean flag = false;

        for (int i = 0; i < answer.length(); i++) {
            if (guess == ans[i]) {
                userAnswer[i] = guess;
                flag = true;
            }
        }

        return flag;
    }

}
