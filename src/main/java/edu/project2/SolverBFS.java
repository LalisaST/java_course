package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SolverBFS implements Solver {

    @Override
    public List<Point> solve(Maze maze, Point start, Point end) {
        if (maze == null || checkingCorrectnessStartEnd(start, maze) || checkingCorrectnessStartEnd(end, maze)) {
            throw new IllegalArgumentException();
        }

        LinkedList<Point> path = new LinkedList<>();
        Map<Point, Point> parent = new HashMap<>();
        Cell[][] grid = maze.getGrid();

        path.add(start);

        while (!path.isEmpty()) {
            Point cur = path.remove();

            if (cur.y <= 0 || cur.x <= 0 || grid == null
                || grid[cur.y][cur.x].type() == Cell.Type.WALL) {
                continue;
            }

            if (cur.y == end.y && cur.x == end.x) {
                return backtrackPath(cur, parent);
            }

            for (Direction direction : Direction.values()) {
                int[] dirs = direction.getMove();
                Point point = new Point(
                    cur.y + dirs[0],
                    cur.x + dirs[1]
                );
                if (parent.containsValue(point)) {
                    continue;
                }
                parent.put(new Point(cur.y + dirs[0], cur.x + dirs[1]), cur);
                path.add(point);
            }
        }
        return Collections.emptyList();
    }

    private List<Point> backtrackPath(Point cur, Map<Point, Point> parent) {
        List<Point> path = new ArrayList<>();
        Point iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = parent.get(iter);
        }

        return path;
    }

    private boolean checkingCorrectnessStartEnd(Point cell, Maze maze) {
        if (cell.x >= maze.getWidth() - 1 || cell.y >= maze.getHeight() - 1 || cell.x <= 0
            || cell.y <= 0) {
            throw new IllegalArgumentException();
        }
        return false;
    }
}
