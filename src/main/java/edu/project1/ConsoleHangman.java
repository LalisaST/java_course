package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    static final int MAXATTEMPTS = 5;
    private final static Logger LOGGER = LogManager.getLogger();
    Scanner scanner = new Scanner(System.in);

    public ConsoleHangman() {

    }

    public void run() {
        String[] food = new String[] {"cake", "pizza", "cookie", "soup", "cheese"};
        FoodDictionary foodDictionary = new FoodDictionary();
        foodDictionary.setFood(food);

        String answer = foodDictionary.randomWord();
        Session session = new Session(answer, MAXATTEMPTS, 0);

        LOGGER.info("> If you want to give up write '0'");

        while (true) {
            LOGGER.info("> Guess a letter:");
            LOGGER.info("< ");
            String input = scanner.nextLine();

            GuessResult guess = tryGuess(session, input);
            printState(guess);

            if (guess instanceof GuessResult.Typo) {
                continue;
            }

            if (guess instanceof GuessResult.Defeat || guess instanceof GuessResult.Win) {
                break;
            }
        }
    }

    private GuessResult tryGuess(Session session, String input) {
        return session.guess(input);
    }

    private void printState(GuessResult guess) {
        LOGGER.info("> The word: " + new String(guess.state()));
        LOGGER.info(guess.message());
    }
}
