package edu.hw6;

import edu.hw6.Task6.PortScanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;

public class PortScannerTest {
    @Test
    @DisplayName("Проверка работы portScanning")
    void checkingWorkPortScanning() {
        assertThatCode(PortScanner::portScanning).doesNotThrowAnyException();
    }
}
