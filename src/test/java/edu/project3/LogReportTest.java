package edu.project3;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LogReportTest {
    @Test
    @DisplayName("Ввод null в конструктор класса")
    void enteringNullIntoTheClassConstructor() {
        assertThatThrownBy(() -> new LogReport(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка функции getTotalRequests")
    void checkingGetTotalRequestsFunction() {
        List<LogRecord> logRecords = Arrays.asList(
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "/", 200, 100, "-", "userAgent"),
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "/", 404, 150, "-", "userAgent")
        );

        LogReport logReport = new LogReport(logRecords);

        assertThat(logReport.getTotalRequests()).isEqualTo(2);
    }

    @Test
    @DisplayName("Проверка функции getTopRequestedResources")
    void checkingGetTopRequestedResources() {
        List<LogRecord> logRecords = Arrays.asList(
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "/path1", 200, 100, "-", "userAgent"),
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "/path2", 404, 150, "-", "userAgent"),
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "/path1", 200, 200, "-", "userAgent")
        );

        LogReport logReport = new LogReport(logRecords);

        assertThat(logReport.getTopRequestedResources()).isEqualTo(Map.of("/path1",2L, "/path2", 1L));
    }

    @Test
    @DisplayName("Проверка функции getTopResponseStatusCodes")
    void checkingGetTopResponseStatusCodes() {
        List<LogRecord> logRecords = Arrays.asList(
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "/", 200, 100, "-", "userAgent"),
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "/", 404, 150, "-", "userAgent"),
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "/", 200, 200, "-", "userAgent")
        );

        LogReport logReport = new LogReport(logRecords);

        assertThat(logReport.getTopResponseStatusCodes()).isEqualTo(Map.of(200, 2L, 404, 1L));
    }

    @Test
    @DisplayName("Проверка функции getAverageResponseSize")
    void checkingGetAverageResponseSize() {
        List<LogRecord> logRecords = Arrays.asList(
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "/", 200, 100, "-", "userAgent"),
            new LogRecord("127.0.0.1", "-", OffsetDateTime.now(), "/", 404, 150, "-", "userAgent")
        );

        LogReport logReport = new LogReport(logRecords);

        assertThat(logReport.getAverageResponseSize()).isEqualTo(125.0);

    }

    @Test
    @DisplayName("Проверка функции getStartDate")
    void checkingGetStartDate() {
        OffsetDateTime now = OffsetDateTime.now();
        List<LogRecord> logRecords = Arrays.asList(
            new LogRecord("127.0.0.1", "-", now.minusDays(1), "/", 200, 100, "-", "userAgent"),
            new LogRecord("127.0.0.1", "-", now, "/", 404, 150, "-", "userAgent")
        );

        LogReport logReport = new LogReport(logRecords);

        assertThat(logReport.getStartDate()).isEqualTo(now.minusDays(1));
    }

    @Test
    @DisplayName("Проверка функции getEndDate")
    void checkingGetEndDate() {
        OffsetDateTime now = OffsetDateTime.now();
        List<LogRecord> logRecords = Arrays.asList(
            new LogRecord("127.0.0.1", "-", now.minusDays(1), "/", 200, 100, "-", "userAgent"),
            new LogRecord("127.0.0.1", "-", now, "/", 404, 150, "-", "userAgent")
        );

        LogReport logReport = new LogReport(logRecords);

        assertThat(logReport.getEndDate()).isEqualTo(now);
    }
}
