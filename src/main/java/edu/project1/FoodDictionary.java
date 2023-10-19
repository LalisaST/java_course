package edu.project1;

import org.jetbrains.annotations.NotNull;

public class FoodDictionary implements Dictionary {
    String[] food;

    public void setFood(String[] food) {
        if (food == null || food.length == 0) {
            throw new IllegalArgumentException();
        }

        this.food = food;
    }

    @Override
    public @NotNull String randomWord() {
        return food[(int) (Math.random() * food.length)];
    }
}
