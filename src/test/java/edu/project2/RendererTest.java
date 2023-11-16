package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RendererTest {
    @Test
    @DisplayName("Проверка отрисовки лабиринта")
    void checkingRenderingOFMaze() {
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
        RenderMaze renderMaze = new RenderMaze();

        String stringMazeTest = renderMaze.render(maze);

        String stringMaze =
            "█████████████████████" + System.lineSeparator() +
            "███               ███" + System.lineSeparator() +
            "███   ███   █████████" + System.lineSeparator() +
            "███   ███   ███   ███" + System.lineSeparator() +
            "███   █████████   ███" + System.lineSeparator() +
            "███               ███" + System.lineSeparator() +
            "█████████████████████" + System.lineSeparator();
        assertThat(stringMazeTest).isEqualTo(stringMaze);
    }

    @Test
    @DisplayName("Проверка отрисовки маршрута")
    void checkingRouteRendering() {
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
        RenderMaze renderMaze = new RenderMaze();

        List<Point> path = List.of(
            new Point(1, 1),
            new Point(2, 1),
            new Point(2, 1),
            new Point(3, 1),
            new Point(4, 1),
            new Point(5, 1),
            new Point(5, 2),
            new Point(5, 3),
            new Point(5, 4),
            new Point(5, 5)
        );

        String stringPathTest = renderMaze.render(maze, path);
        System.out.println(stringPathTest);

        String stringPath =
            "█████████████████████" + System.lineSeparator() +
            "███ ●             ███" + System.lineSeparator() +
            "███ ● ███   █████████" + System.lineSeparator() +
            "███ ● ███   ███   ███" + System.lineSeparator() +
            "███ ● █████████   ███" + System.lineSeparator() +
            "███ ●  ●  ●  ●  ● ███" + System.lineSeparator() +
            "█████████████████████" + System.lineSeparator();
        assertThat(stringPathTest).isEqualTo(stringPath);
    }

    @Test
    @DisplayName("Передача null в render")
    void passingNullToRender() {
        RenderMaze renderer = new RenderMaze();
        assertThatThrownBy(() -> renderer.render(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> renderer.render(null, null)).isInstanceOf(IllegalArgumentException.class);
    }
}
