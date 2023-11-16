package edu.hw6.Task3;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public class Filter {
    private Filter() {}

    public static AbstractFilter readable() {
        return Files::isReadable;
    }

    public static AbstractFilter writable() {
        return Files::isWritable;
    }

    public static AbstractFilter largerThan(long size) {
        return path -> Files.size(path) > size;
    }

    public static AbstractFilter magicNumber(int... magicBytes) {
        return path -> {
            try {
                byte[] fileBytes = Files.readAllBytes(path);
                for (int i = 0; i < magicBytes.length; i++) {
                    if (fileBytes[i] != magicBytes[i]) {
                        return false;
                    }
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        };
    }

    public static AbstractFilter globMatches(String glob) {
        if (glob == null) {
            throw new IllegalArgumentException();
        }

        return path -> {
            PathMatcher m = FileSystems.getDefault().getPathMatcher("glob:" + glob);

            return m.matches(path);
        };
    }

    public static AbstractFilter regexContains(String regex) {
        if (regex == null) {
            throw new IllegalArgumentException();
        }

        return path -> {
            String fileName = path.getFileName().toString();
            Pattern pattern = Pattern.compile(regex);

            return pattern.matcher(fileName).find();
        };
    }
}
