package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites

) {
    static final int FOUR = 4;
    static final int ZERO = 0;
    static final int TWO = 2;
    static final int EIGHT = 8;

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> FOUR;
            case BIRD -> TWO;
            case FISH -> ZERO;
            case SPIDER -> EIGHT;
        };
    }
}
