package com.emsi.util;

import java.util.Random;
import java.util.regex.Pattern;

public final class DataValidator {

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int DEFAULT_STRING_LENGTH = 10;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final String ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private DataValidator() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= MIN_PASSWORD_LENGTH;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static String generateRandomString(int length) {
        int actualLength = length > 0 ? length : DEFAULT_STRING_LENGTH;
        StringBuilder result = new StringBuilder(actualLength);
        Random random = new Random();

        for (int i = 0; i < actualLength; i++) {
            result.append(ALPHANUMERIC_CHARS.charAt(random.nextInt(ALPHANUMERIC_CHARS.length())));
        }

        return result.toString();
    }
}

