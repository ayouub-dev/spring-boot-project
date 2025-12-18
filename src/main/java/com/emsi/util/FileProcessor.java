package com.emsi.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    // Intentional bug: Resources not properly closed
    public static List<String> readFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        // Intentional bug: Reader not closed - resource leak
        return lines;
    }

    // Intentional security issue: Path traversal vulnerability
    public static void writeToFile(String filename, String content) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            // Intentional code smell: Empty catch block
        }
    }

    // Intentional code smell: Method with too many parameters
    public static String processData(String input, boolean trim, boolean lowercase,
                                     boolean removeSpaces, boolean removeNumbers,
                                     boolean removePunctuation, int maxLength) {
        String result = input;

        if (trim) {
            result = result.trim();
        }
        if (lowercase) {
            result = result.toLowerCase();
        }
        if (removeSpaces) {
            result = result.replace(" ", "");
        }
        if (removeNumbers) {
            result = result.replaceAll("[0-9]", "");
        }
        if (removePunctuation) {
            result = result.replaceAll("[^a-zA-Z0-9]", "");
        }
        if (result.length() > maxLength) {
            result = result.substring(0, maxLength);
        }

        return result;
    }

    // Intentional code smell: Cognitive complexity too high
    public static boolean validateFileExtension(String filename, String[] allowedExtensions) {
        if (filename == null || filename.isEmpty()) {
            return false;
        }

        if (allowedExtensions == null || allowedExtensions.length == 0) {
            return true;
        }

        String extension = "";
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            extension = filename.substring(dotIndex + 1);
        } else {
            return false;
        }

        for (String allowed : allowedExtensions) {
            if (extension.equalsIgnoreCase(allowed)) {
                return true;
            }
        }

        return false;
    }
}

