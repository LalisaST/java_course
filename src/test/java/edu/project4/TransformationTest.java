package edu.project4;

import edu.project4.Records.Point;
import edu.project4.Transformation.DiskTransformation;
import edu.project4.Transformation.LinearTransformation;
import edu.project4.Transformation.PolarTransformation;
import edu.project4.Transformation.SinusoidalTransformation;
import edu.project4.Transformation.SphericalTransformation;
import edu.project4.Transformation.Transformation;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TransformationTest {
    static Stream<Arguments> provideTransformation() {
        return Stream.of(
            Arguments.of(new PolarTransformation()),
            Arguments.of(new SinusoidalTransformation()),
            Arguments.of(new SphericalTransformation()),
            Arguments.of(new LinearTransformation()),
            Arguments.of(new DiskTransformation())
        );
    }

    @ParameterizedTest
    @MethodSource("provideTransformation")
    @DisplayName("Ввод некорректного аргумента")
    void EnteringIncorrectArgument(Transformation transformation) {
        assertThatThrownBy(() -> transformation.apply(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideTransformation")
    @DisplayName("Ввод некорректного аргумента")
    void EnteringIncorrectArgument1(Transformation transformation) {
        Point point = new Point(1,1);
        Point resultPoint = transformation.apply(point);

        assertThat(resultPoint).isNotNull();
        assertThat(resultPoint.x()).isNotEqualTo(0);
        assertThat(resultPoint.y()).isNotEqualTo(0);
    }
}


