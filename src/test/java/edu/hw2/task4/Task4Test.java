package edu.hw2.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.task4.Utils.callingInfo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка callingInfo")
    void checkingCallingInfo() {
        try {
            throw new Exception();
        }catch (Exception e) {
            assertThat(callingInfo(e).className()).isEqualTo("edu.hw2.task4.Task4Test");

            assertThat(callingInfo(e).methodName()).isEqualTo("checkingCallingInfo");
        }
    }
}
