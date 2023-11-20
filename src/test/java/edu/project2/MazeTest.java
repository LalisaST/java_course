package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MazeTest {
    @Test
    @DisplayName("Передача некорректных знаечний в Maze")
    void passingInvalidValuesToMaze() {
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

        assertThatThrownBy(() -> new Maze(4, 4, null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Maze(0, 5, grid)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Maze(3, 3, grid)).isInstanceOf(IllegalArgumentException.class);
    }
}
