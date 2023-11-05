package edu.project2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AldousBroderMazeGenerator implements Generator {
    private static final List<String> DIRECTIONS = Arrays.asList("N", "S", "E", "W");
    private static final int MIN_SIZE = 3;
    private static final int FOUR = 4;
    private static final Random RANDOM = new Random();
    private int counter = 0;

    @Override
    public Maze generate(int height, int width) {
        if (height <= MIN_SIZE || width <= MIN_SIZE) {
            throw new IllegalArgumentException();
        }

        int oddHeight = height;
        int oddWidth = width;
        if (height % 2 == 0) {
            oddHeight++;
        }
        if (width % 2 == 0) {
            oddWidth++;
        }

        counter = 0;
        Cell[][] maze = createMaze(oddHeight, oddWidth);

        return creatingPassages(maze, oddHeight, oddWidth);
    }

    private Maze creatingPassages(Cell[][] maze, int oddHeight, int oddWidth) {
        int visitedCells = 1;

        Point p = new Point(1, 1);
        maze[p.y][p.x] = new Cell(Cell.Type.PASSAGE);

        while (visitedCells < counter) {
            int randomInd = RANDOM.nextInt(FOUR);
            String direction = DIRECTIONS.get(randomInd);

            Point a = new Point(p.y, p.x);
            int dx = 0;
            int dy = 0;

            switch (direction) {
                case "N" -> {
                    dy = 1;
                    a.y += 2;
                }
                case "S" -> {
                    dy = -1;
                    a.y -= 2;
                }
                case "E" -> {
                    dx = 1;
                    a.x += 2;
                }
                case "W" -> {
                    dx = -1;
                    a.x -= 2;
                }
                default -> {
                }
            }

            if (a.x <= 0 || a.y <= 0 || a.y >= oddHeight - 1 || a.x >= oddWidth - 1) {
                continue;
            }

            if (maze[a.y][a.x] == null) {
                maze[a.y - dy][a.x - dx] = new Cell(Cell.Type.PASSAGE);
                maze[a.y][a.x] = new Cell(Cell.Type.PASSAGE);
                visitedCells++;
            }
            p.x = a.x;
            p.y = a.y;
        }
        return new Maze(oddHeight, oddWidth, maze);
    }

    private Cell[][] createMaze(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y % 2 != 0 && x % 2 != 0) {
                    counter++;
                } else {
                    grid[y][x] = new Cell(Cell.Type.WALL);
                }
            }
        }
        return grid;
    }
}
