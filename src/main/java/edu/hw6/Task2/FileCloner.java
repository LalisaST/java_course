package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCloner {
    private FileCloner() {}

    public static void cloneFile(Path path) {
        if (path == null || !Files.exists(path)) {
            throw new IllegalArgumentException();
        }

        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        String fileBaseName = (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
        String fileExtension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);

        Path folderPath = path.getParent();

        String newFileName = fileBaseName + " - копия";
        Path newFilePath = folderPath.resolve(newFileName + fileExtension);

        int suffix = 2;
        while (Files.exists(newFilePath)) {
            newFileName = fileBaseName + " - копия (" + suffix + ")";
            newFilePath = folderPath.resolve(newFileName + fileExtension);

            suffix++;
        }

        try {
            Files.copy(path, newFilePath);
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}
