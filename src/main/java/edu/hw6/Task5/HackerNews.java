package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {
    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {

        String resultStr = getRequest("https://hacker-news.firebaseio.com/v0/topstories.json");

        if (resultStr == null || resultStr.equals("null")) {
            return new long[0];
        }

        String[] arrIds = resultStr
            .substring(1, resultStr.length() - 1)
            .split(",");

        long[] result = new long[arrIds.length];

        for (int i = 0; i < arrIds.length; i++) {
            result[i] = Long.parseLong(arrIds[i]);
        }

        return result;

    }

    public static String news(long id) {
        String body = getRequest("https://hacker-news.firebaseio.com/v0/item/" + id + ".json");
        Pattern pattern = Pattern.compile("^.*\"title\":\"(.*)\",\"type\".*$");
        Matcher matcher = pattern.matcher(body);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    private static String getRequest(String uri) {
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
}
