package edu.project2;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;
    static final int MIN_SIZE = 3;

    public Maze(int height, int width, Cell[][] grid) {

        if (height <= MIN_SIZE || width <= MIN_SIZE || grid == null) {
            throw new IllegalArgumentException();
        }

        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
