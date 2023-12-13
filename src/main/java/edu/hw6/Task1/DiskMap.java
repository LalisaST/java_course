package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private final String fileName;
    private final Map<String, String> memoryMap;

    public DiskMap(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.fileName = fileName;

        try {
            new File(fileName).createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.memoryMap = new HashMap<>();
        readingFile();
    }

    private void readingFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    memoryMap.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void writingToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : memoryMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int size() {
        return memoryMap.size();
    }

    @Override
    public boolean isEmpty() {
        return memoryMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return memoryMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return memoryMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return memoryMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String newValue = memoryMap.put(key, value);
        writingToFile();
        return newValue;
    }

    @Override
    public String remove(Object key) {
        String removedValue = memoryMap.remove(key);
        writingToFile();
        return removedValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        memoryMap.putAll(m);
        writingToFile();
    }

    @Override
    public void clear() {
        memoryMap.clear();
        writingToFile();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return memoryMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return memoryMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return memoryMap.entrySet();
    }
}
