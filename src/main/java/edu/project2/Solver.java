package edu.project2;

import java.util.List;

public interface Solver {
    List<Point> solve(Maze maze, Point start, Point end);
}
