package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class SearcherFileByPredicate extends RecursiveTask<List<File>> {
    private final File directory;
    private final Predicate<File> predicate;

    SearcherFileByPredicate(File directory, Predicate<File> predicate) {
        if (directory == null || predicate == null) {
            throw new IllegalArgumentException();
        }

        this.directory = directory;
        this.predicate = predicate;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();
        List<SearcherFileByPredicate> tasks = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    SearcherFileByPredicate subtask = new SearcherFileByPredicate(file, predicate);
                    subtask.fork();
                    tasks.add(subtask);
                } else {
                    if (predicate.test(file)) {
                        result.add(file);
                    }
                }
            }
        }

        for (SearcherFileByPredicate task : tasks) {
            result.addAll(task.join());
        }

        return result;
    }
}
