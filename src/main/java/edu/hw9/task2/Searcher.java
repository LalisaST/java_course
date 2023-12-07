package edu.hw9.task2;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public class Searcher {

    public List<File> searchingDirectories(int count, File directory) {

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(new SearcherDirectories(count - 1, directory));
        }
    }

    public List<File> searchingFileByPredicate(long size, String format, File directory) {
        Predicate<File> sizeAndExtensionPredicate =
            file -> file.length() > size && file.getName().toLowerCase().endsWith(format);

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(new SearcherFileByPredicate(directory, sizeAndExtensionPredicate));
        }
    }
}
