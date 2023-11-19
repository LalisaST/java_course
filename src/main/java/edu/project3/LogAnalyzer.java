package edu.project3;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import static java.net.http.HttpClient.newHttpClient;

public class LogAnalyzer {
    private static final String ERROR_STR_FROM_GLOB = "Error when reading log files from glob.";
    private static final String ADOC = "adoc";
    private static final String MARKDOWN = "markdown";
    public List<String> paths;

    public List<String> getPaths() {
        return paths;
    }

    public void analyzeLogs(String logPath, String fromDate, String toDate, String format) {
        try {
            if (logPath == null) {
                throw new IllegalArgumentException();
            }

            List<String> lines;

            if (logPath.startsWith("http")) {
                lines = List.of(Objects.requireNonNull(getRequest(logPath)).split("\n"));
                paths = List.of(logPath);
            } else {
                lines = getLogFiles(logPath);
            }

            List<LogRecord> logStream = getLogRecords(lines, fromDate, toDate);

            LogReport report = new LogReport(logStream);

            if (format.equals(ADOC)) {
                new ReportsPrinter(ADOC, report, getPaths());
            } else {
                new ReportsPrinter(MARKDOWN, report, getPaths());
            }

        } catch (IOException e) {
            System.err.println("Error when analyzing log files. " + e.getMessage());
        }
    }

    private List<LogRecord> getLogRecords(List<String> lines, String fromDate, String toDate) throws IOException {
        try {
            lines.forEach(System.out::println);

            return lines.stream()
                .map(LogParser::parse)
                .filter(logRecord -> {
                    if (fromDate == null && toDate == null) {
                        return true;
                    }
                    return isWithinDateRange(logRecord.timeLocal(), fromDate, toDate);
                }).toList();

        } catch (Exception e) {
            throw new IOException("Error when reading log files.");
        }
    }

    private List<Path> getLogPaths(String logPath) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + logPath);

        try (Stream<Path> walkedFiles = Files.walk(Paths.get(""))) {
            List<Path> pathsList = walkedFiles
                .filter(path -> Files.isRegularFile(path) && pathMatcher.matches(path)).toList();

            paths = pathsList.stream()
                .map(Path::toString)
                .toList();

            return pathsList;
        } catch (IOException e) {
            throw new RuntimeException(ERROR_STR_FROM_GLOB);
        }
    }

    private List<String> getLogFiles(String logPath) throws IOException {
        List<String> resultinList = new ArrayList<>();

        getLogPaths(logPath).stream()
            .map(path -> {
                try {
                    return Files.readAllLines(path);
                } catch (IOException e) {
                    throw new RuntimeException(ERROR_STR_FROM_GLOB);
                }
            }).toList()
            .forEach(resultinList::addAll);

        return resultinList;
    }

    private String getRequest(String uri) {
        if (uri == null) {
            throw new IllegalArgumentException();
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();

            HttpResponse<String> response = newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (URISyntaxException | InterruptedException | IOException e) {
            return null;
        }
    }

    private boolean isWithinDateRange(OffsetDateTime logDate, String fromDate, String toDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {

            OffsetDateTime fromDateParsed = fromDate != null ? LocalDate
                .parse(fromDate, dateTimeFormatter)
                .atTime(OffsetTime.MIN) : null;
            OffsetDateTime toDateParsed = toDate != null ? LocalDate
                .parse(toDate, dateTimeFormatter)
                .atTime(OffsetTime.MIN) : null;

            if (fromDateParsed != null && toDateParsed != null) {
                return !logDate.isBefore(fromDateParsed) && !logDate.isAfter(toDateParsed);
            } else if (fromDateParsed != null) {
                return !logDate.isBefore(fromDateParsed);
            } else {
                return !logDate.isAfter(toDateParsed);
            }
        } catch (Exception e) {
            System.err.println("Error when parsing dates.");
        }

        return false;
    }

}
