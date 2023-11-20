package edu.project2;

import java.util.List;

public class Main {
    private static final int SIZE = 49;
    private static final int END_POINT = 47;

    private Main() {}

    public static void main(String[] args) {
        Generator generator = new AldousBroderMazeGenerator();
        Solver solver = new SolverBFS();
        generateExample(generator, solver);
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    public static void generateExample(Generator generator, Solver solver) {
        RenderMaze renderMaze = new RenderMaze();
        Maze maze = generator.generate(SIZE, SIZE);

        List<Point> list = solver.solve(maze, new Point(1, 1), new Point(END_POINT, END_POINT));

        System.out.println(renderMaze.render(maze));
        System.out.println();
        System.out.println(renderMaze.render(maze, list));
    }
}
