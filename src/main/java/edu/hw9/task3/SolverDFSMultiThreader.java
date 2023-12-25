package edu.hw9.task3;

import edu.project2.Cell;
import edu.project2.Direction;
import edu.project2.Maze;
import edu.project2.Point;
import edu.project2.Solver;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SolverDFSMultiThreader implements Solver {
    @Override
    public List<Point> solve(Maze maze, Point start, Point end) {
        if (maze == null || checkingCorrectnessStartEnd(start, maze) || checkingCorrectnessStartEnd(end, maze)) {
            throw new IllegalArgumentException();
        }

        Set<Point> path = new LinkedHashSet<>();
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            forkJoinPool.invoke(new DFSTask(maze, start, end, path));
        }

        return path.stream().toList();

    }

    private boolean checkingCorrectnessStartEnd(Point cell, Maze maze) {
        if (cell.x >= maze.getWidth() - 1 || cell.y >= maze.getHeight() - 1 || cell.x <= 0
            || cell.y <= 0 || maze.getGrid() == null) {
            throw new IllegalArgumentException();
        }
        return false;
    }

    private static class DFSTask extends RecursiveTask<Boolean> {
        private final Maze maze;
        private final Point start;
        private final Point end;
        private final Set<Point> path;

        private DFSTask(Maze maze, Point start, Point end, Set<Point> path) {
            this.maze = maze;
            this.start = start;
            this.end = end;
            this.path = path;
        }

        @Override
        protected Boolean compute() {
            int height = start.y;
            int width = start.x;
            Cell[][] grid = maze.getGrid();
            Point p = new Point(height, width);

            if (grid[height][width].type() == Cell.Type.WALL || path.contains(p)) {
                return false;
            }


            path.add(p);

            if (height == end.y && width == end.x) {
                return true;
            }

            List<DFSTask> tasks = new ArrayList<>();
            for (Direction direction : Direction.values()) {
                int[] dirs = direction.getMove();
                Point point = new Point(
                    height + dirs[0],
                    width + dirs[1]
                );
                DFSTask subtasks = new DFSTask(maze, point, end, new LinkedHashSet<>(path));
                subtasks.fork();
                tasks.add(subtasks);
            }

            for (DFSTask task : tasks) {
                if (task.join()) {
                    path.addAll(task.path);
                    return true;
                }
            }

            path.clear();
            return false;
        }
    }
}
