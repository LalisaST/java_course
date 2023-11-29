package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int MAX_CONNECT = 5;
    private static final int PORT = 8080;
    private static final int MAX_SIZE_BUF = 1024;
    private static final int TIMEOUT = 30;
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(MAX_CONNECT);
    private static boolean running = false;
    private static final Map<String, String> QUOTES = new HashMap<>();

    private Server() {}

    static {
        QUOTES.put("личности", "Не переходи на личности там, где их нет");
        QUOTES.put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        );
        QUOTES.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        );
        QUOTES.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    public static void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            serverSocket.setSoTimeout(TIMEOUT);
            running = true;
            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    EXECUTOR_SERVICE.submit(() -> handleClient(clientSocket));
                } catch (SocketTimeoutException ignored) {
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void stop() {
        running = false;
    }

    private static void handleClient(Socket clientSocket) {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream();
             clientSocket
        ) {
            byte[] buffer = new byte[MAX_SIZE_BUF];
            int bytesRead = inputStream.read(buffer);
            String keyword = new String(buffer, 0, bytesRead).strip();

            String response = getResponse(keyword);
            outputStream.write(response.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String getResponse(String keyword) {
        return QUOTES.getOrDefault(keyword, "Неизветсное ключевое слово");
    }

}
