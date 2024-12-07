package com.github.simons0506;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileReader {

    private FileReader() {
    }

    public static List<String> read(String fileName) {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new RuntimeException("Datei " + fileName + " wurde nicht gefunden.");
        }
        try (Stream<String> lineStream = Files.lines(Paths.get(resource.toURI()))) {
            return lineStream.toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
