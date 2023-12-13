package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private static final int PORT = 8080;
    private static final int MAX_SIZE_BUF = 1024;

    private Client() {
    }

    public static String start(String word) {
        if (word == null || word.isBlank()) {
            throw new IllegalArgumentException();
        }

        try (Socket socket = new Socket("localhost", PORT);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            outputStream.write(word.getBytes());

            byte[] buffer = new byte[MAX_SIZE_BUF];
            int bytesRead = inputStream.read(buffer);
            String response = new String(buffer, 0, bytesRead);

            return "Сервер: " + response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
