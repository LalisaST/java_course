package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReportPrinterTest {
    @Test
    @DisplayName("Ввод некорректного LogReport")
    void enteringIncorrectLogReport() {
        List<String> paths = List.of("file1.log", "file2.log");
        assertThatThrownBy(() -> new ReportsPrinter(
            "markdown",
            null,
            paths
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ввод некорректного путя")
    void enteringIncorrectPath() {
        LogReport logReport = new LogReport(List.of());
        assertThatThrownBy(() -> new ReportsPrinter(
            "markdown",
            logReport,
            null
        )).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new ReportsPrinter("markdown", logReport, List.of())).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ввод формата = null")
    void enteringFormatEqualNull() throws IOException {
        List<LogRecord> logRecords = List.of(
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "HEAD", "/", "HTTP/1.1", 200, 100, "-", "userAgent")
        );
        LogReport validReport = new LogReport(logRecords);
        ReportsPrinter printer = new ReportsPrinter(null, validReport, List.of("path"));
        assertThat(printer.format).isEqualTo("mardown");
        Files.delete(Path.of("report.md"));
    }

    @Test
    @DisplayName("Проверка создания файла")
    void checkingFileCreation() throws IOException {
        List<LogRecord> logRecords = List.of(
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "HEAD", "/", "HTTP/1.1", 200, 100, "-", "userAgent")
        );
        LogReport validReport = new LogReport(logRecords);
        new ReportsPrinter("adoc", validReport, List.of("path"));
        assertThat(Files.deleteIfExists(Path.of("report.adoc"))).isTrue();
    }
}
