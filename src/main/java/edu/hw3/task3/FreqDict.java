package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreqDict {
    private FreqDict() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> freqMap = new HashMap<>();

        if (list == null) {
            throw new IllegalArgumentException();
        }

        for (T item : list) {
            if (item == null) {
                throw new IllegalArgumentException();
            }
            freqMap.put(item, freqMap.getOrDefault(item, 0) + 1);
        }

        return freqMap;
    }
}
