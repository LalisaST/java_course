package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AldousBroderMazeGeneratorTest {
    static Stream<Arguments> provideInvalidArguments() {
        return Stream.of(
            Arguments.of(3,3),
            Arguments.of(-1, -5)
        );
    }

    @DisplayName("Ввод недопустимых значений в generate")
    @ParameterizedTest
    @MethodSource("provideInvalidArguments")
    void enteringInvalidValuesGenerate(int height, int width) {
        AldousBroderMazeGenerator aldousBroderMazeGenerator = new AldousBroderMazeGenerator();

        assertThatThrownBy(() -> aldousBroderMazeGenerator.generate(height, width)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка генерации")
    void checkingGeneration() {
        int n = 10;
        int m = 10;
        AldousBroderMazeGenerator aldousBroderMazeGenerator = new AldousBroderMazeGenerator();
        Maze maze = aldousBroderMazeGenerator.generate(n, m);
        assertThat(maze).isNotNull();

        for(Cell[] row: maze.getGrid()) {
            for (Cell cell: row) {
                assertThat(cell).isNotNull();
            }
        }
    }
}
