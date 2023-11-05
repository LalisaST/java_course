package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolverDFS implements Solver {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    @Override
    public List<Point> solve(Maze maze, Point start, Point end) {
        if (maze == null || checkingCorrectnessStartEnd(start, maze) || checkingCorrectnessStartEnd(end, maze)) {
            throw new IllegalArgumentException();
        }

        List<Point> path = new ArrayList<>();
        Cell[][] grid = maze.getGrid();

        if (explore(grid, start.y, start.x, path, end)) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean explore(Cell[][] grid, int height, int width, List<Point> path, Point end) {
        if (height <= 0 || width <= 0 || grid == null
            || grid[height][width].type() == Cell.Type.WALL
            || path.contains(new Point(height, width))) {
            return false;
        }

        path.add(new Point(height, width));

        if (height == end.y && width == end.x) {
            return true;
        }

        for (int[] direction : DIRECTIONS) {
            Point point = new Point(
                height + direction[0],
                width + direction[1]
            );
            if (explore(grid, point.y, point.x, path, end)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private boolean checkingCorrectnessStartEnd(Point cell, Maze maze) {
        if (cell.x >= maze.getWidth() - 1 || cell.y >= maze.getHeight() - 1 || cell.x <= 0
            || cell.y <= 0) {
            throw new IllegalArgumentException();
        }
        return false;
    }
}
