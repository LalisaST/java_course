package edu.hw6.Task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default AbstractFilter and(AbstractFilter abstractFilter) {
        if (abstractFilter == null) {
            throw new IllegalArgumentException();
        }
        return path -> this.accept(path) && abstractFilter.accept(path);
    }
}

