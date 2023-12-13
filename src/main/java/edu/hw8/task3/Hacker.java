package edu.hw8.task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Hacker {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int PASSWORD_LENGTH = 4;
    private static final String FILE_NAME = "src/main/resources/dataBase.txt";

    public Map<String, String> hackingPasswords(int numThreads) {
        if (numThreads == 1) {
            return passwordSearch(readingDatabase());
        } else if (numThreads > 1) {
            return passwordSearchMultithreaded(readingDatabase(), numThreads);
        }
        throw new IllegalArgumentException();
    }

    private Map<String, String> readingDatabase() {
        Map<String, String> data = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split("\\s+");
                data.put(str[1], str[0]);
            }

            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> passwordSearch(Map<String, String> passwordDatabase) {
        Map<String, String> result = new HashMap<>();

        for (int i = 0; i < Math.pow(CHARACTERS.length(), PASSWORD_LENGTH); i++) {
            String generatedPassword = nextPassword(i);
            String hashedGeneratedPassword = getEncodedString(generatedPassword);

            if (passwordDatabase.containsKey(hashedGeneratedPassword)) {
                result.put(passwordDatabase.get(hashedGeneratedPassword), generatedPassword);
            }
        }

        return result;
    }

    private Map<String, String> passwordSearchMultithreaded(Map<String, String> passwordDatabase, int numThreads) {
        Map<String, String> result = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        int elementsTask = (int) (Math.pow(CHARACTERS.length(), PASSWORD_LENGTH) / numThreads);
        int remainingElements = (int) (Math.pow(CHARACTERS.length(), PASSWORD_LENGTH) % numThreads);

        int start = 0;

        for (int j = 0; j < numThreads; j++) {
            int elements = start + elementsTask + (j < remainingElements ? 1 : 0);

            int finalStart = start;

            executorService.execute(() -> {
                for (int i = finalStart; i < elements; i++) {
                    String generatedPassword = nextPassword(i);
                    String hashedGeneratedPassword = getEncodedString(generatedPassword);

                    if (passwordDatabase.containsKey(hashedGeneratedPassword)) {
                        result.put(passwordDatabase.get(hashedGeneratedPassword), generatedPassword);
                    }
                }

            });

            start = elements;
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private String nextPassword(int index) {
        int ind = index;
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < Hacker.PASSWORD_LENGTH; i++) {
            int charIndex = ind % Hacker.CHARACTERS.length();
            password.append(Hacker.CHARACTERS.charAt(charIndex));
            ind /= Hacker.CHARACTERS.length();
        }

        return password.toString();
    }

    private String getEncodedString(String string) {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        md5.update(StandardCharsets.UTF_8.encode(string));

        return String.format("%032x", new BigInteger(1, md5.digest()));
    }
}
