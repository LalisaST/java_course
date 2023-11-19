package edu.project3;

import java.time.OffsetDateTime;

public record LogRecord(
    String remoteAddr,
    String remoteUser,
    OffsetDateTime timeLocal,
    String method,
    String request,
    String version,
    int status,
    long bodyBytesSent,
    String httpReferer,
    String httpUserAgent
) {
}
