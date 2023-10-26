package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public class Clusterize {

    private Clusterize() {
    }

    public static List<String> clusterize(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        String strWithoutSpaces = str.replace(" ", "");

        List<String> clusters = new ArrayList<>();
        int startIndex = 0;
        int counter = 0;

        for (int i = 0; i < strWithoutSpaces.length(); i++) {
            char bracket = strWithoutSpaces.charAt(i);
            if (bracket == '(') {
                counter++;
            } else if (bracket == ')') {
                if (counter == 0) {
                    throw new IllegalArgumentException();
                }

                counter--;

                if (counter == 0) {
                    clusters.add(strWithoutSpaces.substring(startIndex, i + 1));
                    startIndex = i + 1;
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (counter != 0) {
            throw new IllegalArgumentException();
        }

        return clusters;
    }
}
