package com.emsi.util;

    import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class FileProcessor {

    private static final Logger logger = LoggerFactory.getLogger(FileProcessor.class);
    private static final int MAX_LENGTH_DEFAULT = 1000;

    private FileProcessor() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    public static List<String> readFile(String filePath) throws IOException {
        validateFilePath(filePath);
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    public static void writeToFile(String filename, String content) throws IOException {
        validateFilePath(filename);
        Path path = Paths.get(filename).normalize();

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(content);
        } catch (IOException e) {
            logger.error("Failed to write to file: {}", filename, e);
            throw e;
        }
    }

    public static String processData(String input, ProcessingOptions options) {
        if (input == null) {
            return "";
        }

        String result = input;

        if (options.isTrim()) {
            result = result.trim();
        }
        if (options.isLowercase()) {
            result = result.toLowerCase();
        }
        if (options.isRemoveSpaces()) {
            result = result.replace(" ", "");
        }
        if (options.isRemoveNumbers()) {
            result = result.replaceAll("[0-9]", "");
        }
        if (options.isRemovePunctuation()) {
            result = result.replaceAll("[^a-zA-Z0-9]", "");
        }

        int maxLength = options.getMaxLength() > 0 ? options.getMaxLength() : MAX_LENGTH_DEFAULT;
        if (result.length() > maxLength) {
            result = result.substring(0, maxLength);
        }

        return result;
    }

    public static boolean validateFileExtension(String filename, String[] allowedExtensions) {
        if (filename == null || filename.isEmpty()) {
            return false;
        }

        if (allowedExtensions == null || allowedExtensions.length == 0) {
            return true;
        }

        String extension = getFileExtension(filename);
        if (extension.isEmpty()) {
            return false;
        }

        for (String allowed : allowedExtensions) {
            if (extension.equalsIgnoreCase(allowed)) {
                return true;
            }
        }

        return false;
    }

    private static String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1);
        }

        return "";
    }

    private static void validateFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }

        Path path = Paths.get(filePath).normalize();
        if (path.toString().contains("..")) {
            throw new SecurityException("Path traversal attempt detected");
        }
    }

    public static class ProcessingOptions {
        private boolean trim;
        private boolean lowercase;
        private boolean removeSpaces;
        private boolean removeNumbers;
        private boolean removePunctuation;
        private int maxLength;

        public boolean isTrim() { return trim; }
        public void setTrim(boolean trim) { this.trim = trim; }

        public boolean isLowercase() { return lowercase; }
        public void setLowercase(boolean lowercase) { this.lowercase = lowercase; }

        public boolean isRemoveSpaces() { return removeSpaces; }
        public void setRemoveSpaces(boolean removeSpaces) { this.removeSpaces = removeSpaces; }

        public boolean isRemoveNumbers() { return removeNumbers; }
        public void setRemoveNumbers(boolean removeNumbers) { this.removeNumbers = removeNumbers; }

        public boolean isRemovePunctuation() { return removePunctuation; }
        public void setRemovePunctuation(boolean removePunctuation) { this.removePunctuation = removePunctuation; }

        public int getMaxLength() { return maxLength; }
        public void setMaxLength(int maxLength) { this.maxLength = maxLength; }
    }
}

