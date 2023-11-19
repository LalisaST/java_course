package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StatusCodesTest {
    @Test
    @DisplayName("Ввод несуществующего кода")
    void enteringEmptyString() {
        assertThatThrownBy(() -> StatusCodes.getStatusCode(0)).isInstanceOf(IllegalArgumentException.class);
    }
}
