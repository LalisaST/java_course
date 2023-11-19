package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("MultipleStringLiterals")
public class ReportsPrinter {
    String format;
    LogReport report;
    List<String> path;

    public ReportsPrinter(String format, LogReport report, List<String> path) {
        if (report == null || path == null || path.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.path = path;
        this.report = report;
        this.format = Objects.requireNonNullElse(format, "mardown");

        if (this.format.equals("adoc")) {
            toAsciiDoc(report, path);
        } else {
            toMarkdown(report, path);
        }
    }

    private static void createFile(String format, StringBuilder stringBuilder) {
        try {
            Path report = Path.of("report" + format);
            Files.writeString(report, stringBuilder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void toMarkdown(LogReport report, List<String> path) {
        if (report == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder markdown = new StringBuilder();

        writingGeneralInformationToMarkdown(markdown, report, path);
        writingRequestedResourcesToMarkdown(markdown, report);
        writingResponseCodesToMarkdown(markdown, report);

        createFile(".md", markdown);
    }

    private static void writingGeneralInformationToMarkdown(
        StringBuilder stringBuilder,
        LogReport report,
        List<String> path
    ) {
        stringBuilder.append("#### Общая информация")
            .append(System.lineSeparator())
            .append(System.lineSeparator());
        stringBuilder.append("Метрика | Значение")
            .append(System.lineSeparator());
        stringBuilder.append("|---|---|")
            .append(System.lineSeparator());
        printPaths(report, stringBuilder, path);
    }

    private static void writingRequestedResourcesToMarkdown(StringBuilder stringBuilder, LogReport report) {
        stringBuilder.append("#### Запрашиваемые ресурсы")
            .append(System.lineSeparator())
            .append(System.lineSeparator());
        stringBuilder.append("Ресурс | Количество").append(System.lineSeparator());
        stringBuilder.append("|---|---|").append(System.lineSeparator());
        for (Map.Entry<String, Long> entry : report.getTopRequestedResources().entrySet()) {
            stringBuilder.append(String.format("| `%s` | %,d |", entry.getKey(), entry.getValue()))
                .append(System.lineSeparator());
        }
        stringBuilder.append(System.lineSeparator());
    }

    private static void writingResponseCodesToMarkdown(StringBuilder stringBuilder, LogReport report) {
        stringBuilder.append("#### Коды ответа")
            .append(System.lineSeparator())
            .append(System.lineSeparator());
        stringBuilder.append("Код | Имя | Количество")
            .append(System.lineSeparator());
        stringBuilder.append("|---|---|---|")
            .append(System.lineSeparator());
        for (Map.Entry<Integer, Long> entry : report.getTopResponseStatusCodes().entrySet()) {
            stringBuilder.append(String.format(
                "%d | %s | %,d",
                entry.getKey(),
                StatusCodes.getStatusCode(entry.getKey()),
                entry.getValue()
            )).append(System.lineSeparator());
        }
    }

    private static void printPaths(LogReport report, StringBuilder stringBuilder, List<String> path) {

        stringBuilder.append(String.format("|Файл(-ы) | %s", String.join(", ", path))
        ).append(System.lineSeparator());

        OffsetDateTime startDate =  report.getStartDate();
        OffsetDateTime endDate = report.getEndDate();

        stringBuilder.append(String.format(
                "|Начальная дата | %s",
                startDate == null ? "-" : startDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
            ))
            .append(System.lineSeparator());
        stringBuilder.append(String.format(
                "|Конечная дата | %s",
                endDate == null ? "-" : endDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
            ))
            .append(System.lineSeparator());
        stringBuilder.append(String.format("|Количество запросов | %,d", report.getTotalRequests()))
            .append(System.lineSeparator());
        stringBuilder.append(String.format("|Средний размер ответа | %s", report.getAverageResponseSize()))
            .append(System.lineSeparator());
    }

    private static void toAsciiDoc(LogReport report, List<String> path) {
        StringBuilder asciiDoc = new StringBuilder();

        writingGeneralInformationToAsciiDoc(asciiDoc, report, path);
        writingRequestedResourcesToAsciiDoc(asciiDoc, report);
        writingResponseCodesToAsciiDoc(asciiDoc, report);

        createFile(".adoc", asciiDoc);
    }

    private static void writingGeneralInformationToAsciiDoc(
        StringBuilder stringBuilder,
        LogReport report,
        List<String> path
    ) {
        stringBuilder.append("==== Общая информация")
            .append(System.lineSeparator())
            .append(System.lineSeparator());

        stringBuilder.append("|====").append(System.lineSeparator());
        stringBuilder.append("|Метрика |Значение")
            .append(System.lineSeparator());
        printPaths(report, stringBuilder, path);
        stringBuilder.append("|====").append(System.lineSeparator())
            .append(System.lineSeparator());
    }

    private static void writingRequestedResourcesToAsciiDoc(StringBuilder stringBuilder, LogReport report) {
        stringBuilder.append("==== Запрашиваемые ресурсы")
            .append(System.lineSeparator())
            .append(System.lineSeparator());

        stringBuilder.append("|====").append(System.lineSeparator());
        stringBuilder.append("|Ресурс |Количество")
            .append(System.lineSeparator());
        for (Map.Entry<String, Long> entry : report.getTopRequestedResources().entrySet()) {
            stringBuilder.append(String.format("|`%s` | %,d", entry.getKey(), entry.getValue()))
                .append(System.lineSeparator());
        }
        stringBuilder.append("|====").append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());
    }

    private static void writingResponseCodesToAsciiDoc(StringBuilder stringBuilder, LogReport report) {
        stringBuilder.append("==== Коды ответа")
            .append(System.lineSeparator())
            .append(System.lineSeparator());
        stringBuilder.append("|====").append(System.lineSeparator());
        stringBuilder.append("|Код |Имя |Количество")
            .append(System.lineSeparator());
        for (Map.Entry<Integer, Long> entry : report.getTopResponseStatusCodes().entrySet()) {
            stringBuilder.append(String.format(
                "|%d | %s | %,d",
                entry.getKey(),
                StatusCodes.getStatusCode(entry.getKey()),
                entry.getKey()
            )).append(System.lineSeparator());
        }
        stringBuilder.append("|====").append(System.lineSeparator());
    }
}
