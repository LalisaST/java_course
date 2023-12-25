package edu.hw9.task3;

import edu.project2.AldousBroderMazeGenerator;
import edu.project2.Generator;
import edu.project2.Maze;
import edu.project2.Point;
import edu.project2.RenderMaze;
import edu.project2.Renderer;
import edu.project2.Solver;

@SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
public class Main {
    private Main() {}

    public static void main(String[] args) {
        Generator generator = new AldousBroderMazeGenerator();
        Maze maze = generator.generate(30, 30);
        Renderer renderer = new RenderMaze();

        System.out.println(renderer.render(maze));
        System.out.println();

        Solver solver = new SolverDFSMultiThreader();
        var list = solver.solve(maze, new Point(1, 1), new Point(29, 29));
        System.out.println(renderer.render(maze, list));
    }
}
