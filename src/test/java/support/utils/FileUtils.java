package support.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static <T> T getObjectFromResourceFile(String path, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(getResourceFileAsString(path), clazz);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static String getResourceFileAsString(@NonNull String filePath) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(filePath);
        if (url == null) {
            throw new IllegalArgumentException("Url is null. Check your filePath");
        }
        try {
            return Files.readString(Paths.get(url.toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
