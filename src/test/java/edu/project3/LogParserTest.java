package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LogParserTest {

    @Test
    @DisplayName("Ввод корректного формата строки")
    void enteringCorrectStringFormat() {
        String logLine = "217.168.17.5 - aboba [17/May/2015:08:05:02 +0000] \"GET /path HTTP/1.1\" 200 1234 \"referer\" \"userAgent\"";
        LogRecord logRecord = LogParser.parse(logLine);

        assertThat(logRecord).isNotNull();
        assertThat(logRecord.remoteAddr()).isEqualTo("217.168.17.5");
        assertThat(logRecord.remoteUser()).isEqualTo("aboba");
        assertThat(logRecord.method()).isEqualTo("GET");
        assertThat(logRecord.request()).isEqualTo("/path");
        assertThat(logRecord.version()).isEqualTo("HTTP/1.1");
        assertThat(logRecord.status()).isEqualTo(200);
        assertThat(logRecord.bodyBytesSent()).isEqualTo(1234);
        assertThat(logRecord.httpReferer()).isEqualTo("referer");
        assertThat(logRecord.httpUserAgent()).isEqualTo("userAgent");
    }

    @Test
    @DisplayName("Ввод null")
    void enteringNull() {
        assertThatThrownBy(() -> LogParser.parse(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Ввод некорректного формата строки")
    void enteringIncorrectStringFormat() {
        assertThatThrownBy(() -> LogParser.parse("Invalid log line")).isInstanceOf(IllegalArgumentException.class);
    }
}
