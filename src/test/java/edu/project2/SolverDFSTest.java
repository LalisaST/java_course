package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SolverDFSTest {
    static Stream<Arguments> provideInvalidArguments() {
        return Stream.of(
            Arguments.of(null, new Point(1, 1), new Point(7, 7)),
            Arguments.of(new Maze(5, 5, new Cell[][] {}), new Point(0, 0), new Point(7, 7)),
            Arguments.of(new Maze(5, 5, new Cell[][] {}), new Point(1, 1), new Point(4, 4))
        );
    }

    @DisplayName("Ввод недопустимых значений в solve")
    @ParameterizedTest
    @MethodSource("provideInvalidArguments")
    void enteringInvalidValuesSolve(Maze maze, Point start, Point end) {
        SolverDFS solverDFS = new SolverDFS();

        assertThatThrownBy(() -> solverDFS.solve(maze, start, end)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Solver не находит путь")
    void solverDoesntFindPath() {
        Cell[][] grid = {
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE),
                new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)}
        };

        Maze maze = new Maze(7, 7, grid);
        SolverDFS solver = new SolverDFS();

        assertThat(solver.solve(maze, new Point(1, 1), new Point(5, 5))).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Solver находит путь")
    void solverFindPath() {
        Cell[][] grid = {
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)}
        };

        Maze maze = new Maze(7, 7, grid);
        SolverDFS solver = new SolverDFS();

        List<Point> solverTest = solver.solve(maze, new Point(1, 1), new Point(5, 5));
        List<Point> prescribedSolver = List.of(new Point(1, 1),
            new Point(2, 1),
            new Point(3, 1),
            new Point(4, 1),
            new Point(5, 1),
            new Point(5, 2),
            new Point(5, 3),
            new Point(5, 4),
            new Point(5, 5)
        );

        assertThat(solverTest).isEqualTo(prescribedSolver);
    }
}
