package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimMazeGenerator implements Generator {
    static final int MIN_SIZE = 3;

    @Override
    public Maze generate(int height, int width) {
        if (height <= MIN_SIZE || width <= MIN_SIZE) {
            throw new IllegalArgumentException();
        }

        Cell[][] maze = createMaze(height, width);
        Point p = new Point(1, 1);
        maze[p.y][p.x] = new Cell(Cell.Type.PASSAGE);

        ArrayList<Point> walls = new ArrayList<>();
        newWalls(walls, p, maze, height, width);

        Random rand = new Random();

        while (!walls.isEmpty()) {
            int randomIndex = rand.nextInt(walls.size());
            p = walls.remove(randomIndex);

            if (breakWall(p, maze, height, width)) {
                newWalls(walls, p, maze, height, width);
            }
        }
        return new Maze(height, width, maze);
    }

    private List<Point> neighbors(Point p) {
        return List.of(
            new Point(p.y, p.x + 1),
            new Point(p.y, p.x - 1),
            new Point(p.y - 1, p.x),
            new Point(p.y + 1, p.x)
        );
    }

    private void newWalls(ArrayList<Point> walls, Point p, Cell[][] maze, int n, int m) {
        List<Point> nbrs = neighbors(p);
        for (Point a : nbrs) {
            if (0 < a.y && a.y < n - 1 && 0 < a.x && a.x < m - 1 && maze[a.y][a.x].type() != Cell.Type.PASSAGE) {
                walls.add(a);
            }
        }
    }

    private boolean breakWall(Point p, Cell[][] maze, int n, int m) {
        List<Point> nbrs = neighbors(p);
        int whiteCount = 0;
        Point whiteNeighbor = null;

        for (Point a : nbrs) {
            if (a.y >= 0 && a.y < n - 1 && a.x >= 0 && a.x < m - 1 && maze[a.y][a.x].type() == Cell.Type.PASSAGE) {
                whiteCount++;
                whiteNeighbor = a;
            }
        }

        int ny;
        int nx;

        if (whiteCount == 1) {
            if (p.y == whiteNeighbor.y) {
                ny = p.y;
            } else if (p.y > whiteNeighbor.y) {
                if (p.y + 1 < n - 1) {
                    ny = p.y + 1;
                } else {
                    ny = p.y;
                }
            } else {
                if (p.y - 1 > 0) {
                    ny = p.y - 1;
                } else {
                    ny = p.y;
                }
            }

            if (p.x == whiteNeighbor.x) {
                nx = p.x;
            } else if (p.x > whiteNeighbor.x) {
                if (p.x + 1 < m - 1) {
                    nx = p.x + 1;
                } else {
                    nx = p.x;
                }
            } else {
                if (p.x + 1 > 0) {
                    nx = p.x - 1;
                } else {
                    nx = p.x;
                }
            }

            maze[p.y][p.x] = new Cell(Cell.Type.PASSAGE);
            maze[ny][nx] = new Cell(Cell.Type.PASSAGE);
            p.y = ny;
            p.x = nx;
            return true;
        }
        return false;
    }

    private Cell[][] createMaze(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(Cell.Type.WALL);
            }
        }
        return grid;
    }
}

