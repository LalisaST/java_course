package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class OutputStreamsComposition {
    private OutputStreamsComposition() {}

    public static void compose(Path path) {
        if (path == null) {
            throw new IllegalArgumentException();
        }

        try (
            OutputStream os = Files.newOutputStream(path);
            CheckedOutputStream cos = new CheckedOutputStream(os, new Adler32());
            BufferedOutputStream bos = new BufferedOutputStream(cos);
            OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8);
            PrintWriter pw = new PrintWriter(osw)
        ) {
            pw.write("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
