package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LogAnalyzerTest {
    @Test
    @DisplayName("Ввод корректного путя(ссылка)")
    void enteringCorrectPathsURI() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        String validLogPath =
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

        logAnalyzer.analyzeLogs(validLogPath, null, null, "adoc");

        assertThat(logAnalyzer.getPaths()).isNotEmpty();
    }

    @Test
    @DisplayName("Ввод корректного путя(файл)")
    void enteringCorrectPathsFile() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        String validLogPath = "src/main/resources/abobaLog.txt";

        logAnalyzer.analyzeLogs(validLogPath, null, null, "adoc");

        assertThat(logAnalyzer.getPaths()).isNotEmpty();
    }

    @Test
    @DisplayName("Ввод некорректного путя")
    void enteringInCorrectPaths() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();

        assertThatThrownBy(() -> logAnalyzer.analyzeLogs(null, null, null, "adoc"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
