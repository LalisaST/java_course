package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SearcherDirectories extends RecursiveTask<List<File>> {
    private final int filesCount;
    private final File directory;

    SearcherDirectories(int filesCount, File directory) {
        if (filesCount <= 0 || directory == null) {
            throw new IllegalArgumentException();
        }

        this.filesCount = filesCount;
        this.directory = directory;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();
        List<SearcherDirectories> tasks = new ArrayList<>();
        int fileCount = 0;

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    SearcherDirectories subtask = new SearcherDirectories(filesCount, file);
                    subtask.fork();
                    tasks.add(subtask);
                } else {
                    fileCount++;
                }
            }
        }

        for (SearcherDirectories task : tasks) {
            result.addAll(task.join());
        }

        if (fileCount > filesCount) {
            result.add(directory);
        }

        return result;
    }
}
