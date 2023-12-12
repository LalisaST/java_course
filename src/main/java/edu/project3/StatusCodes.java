package edu.project3;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("MagicNumber")
public class StatusCodes {
    private static final Map<Integer, String> CODES = new HashMap<>();

    private StatusCodes() {
    }

    static {
        CODES.put(100, "Continue");
        CODES.put(101, "Switching Protocols");
        CODES.put(102, "Processing");
        CODES.put(103, "Early Hints");
        CODES.put(200, "OK");
        CODES.put(201, "Created");
        CODES.put(202, "Accepted");
        CODES.put(203, "Non-Authoritative Information");
        CODES.put(204, "No Content");
        CODES.put(205, "Reset Content");
        CODES.put(206, "Partial Content");
        CODES.put(207, "Multi-Status");
        CODES.put(208, "Already Reported");
        CODES.put(226, "IM Used");
        CODES.put(300, "Multiple Choices");
        CODES.put(301, "Moved Permanently");
        CODES.put(302, "Found");
        CODES.put(303, "See Other");
        CODES.put(304, "Not Modified");
        CODES.put(305, "Use Proxy");
        CODES.put(306, "unused");
        CODES.put(307, "Temporary Redirect");
        CODES.put(308, "Permanent Redirect");
        CODES.put(400, "Bad Request");
        CODES.put(401, "Unauthorized");
        CODES.put(402, "Payment Required");
        CODES.put(403, "Forbidden");
        CODES.put(404, "Not Found");
        CODES.put(405, "Method Not Allowed");
        CODES.put(406, "Not Acceptable");
        CODES.put(407, "Proxy Authentication Required");
        CODES.put(408, "Request Timeout");
        CODES.put(409, "Conflict");
        CODES.put(410, "Gone");
        CODES.put(411, "Length Required");
        CODES.put(412, "Precondition Failed");
        CODES.put(413, "Payload Too Large");
        CODES.put(414, "URI Too Long");
        CODES.put(415, "Unsupported Media Type");
        CODES.put(416, "Range Not Satisfiable");
        CODES.put(417, "Expectation Failed");
        CODES.put(418, "I'm a teapot");
        CODES.put(421, "Misdirected Request");
        CODES.put(422, "Unprocessable Content");
        CODES.put(423, "Locked");
        CODES.put(424, "Failed Dependency");
        CODES.put(425, "Too Early");
        CODES.put(426, "Upgrade Required");
        CODES.put(428, "Precondition Required");
        CODES.put(429, "Too Many Requests");
        CODES.put(431, "Request Header Fields Too Large");
        CODES.put(500, "Internal Server Error");
        CODES.put(501, "Not Implemented");
        CODES.put(502, "Bad Gateway");
        CODES.put(503, "Service Unavailable");
        CODES.put(504, "Gateway Timeout");
        CODES.put(505, "HTTP Version Not Supported");
        CODES.put(506, "Variant Also Negotiates");
        CODES.put(507, "Unavailable For Legal Reasons");
        CODES.put(508, "Loop Detected");
        CODES.put(510, "Not Extended");
        CODES.put(511, "Network Authentication Required");
    }

    public static String getStatusCode(int code) {
        if (!CODES.containsKey(code)) {
            throw new IllegalArgumentException();
        }

        return CODES.get(code);
    }
}
