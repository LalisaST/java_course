package edu.hw3.task6;

public record Stock(int price, String name) {
    public Stock {

        if (name == null) {
            throw new IllegalArgumentException();
        }

        String correctedName = name.trim();
        if (price <= 0 || correctedName.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
