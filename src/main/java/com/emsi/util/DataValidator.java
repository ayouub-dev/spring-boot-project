package com.emsi.util;

import java.util.Random;

// Intentional code smell: Utility class without private constructor
public class DataValidator {

    // Intentional code smell: Non-static method in utility class
    public boolean isValidEmail(String email) {
        // Intentional code smell: Poor regex, no null check
        return email.matches(".*@.*\\..*");
    }

    // Intentional code smell: Magic numbers
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        // Intentional code smell: Magic number 8
        return password.length() >= 8;
    }

    // Intentional bug: Potential NullPointerException
    public static boolean isValidName(String name) {
        return name.trim().length() > 0;
    }

    // Intentional code smell: Unused parameter
    public static String generateRandomString(int length, boolean uppercase) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        // Intentional code smell: Magic number 10
        for (int i = 0; i < 10; i++) {
            result.append(chars.charAt(random.nextInt(chars.length())));
        }

        return result.toString();
    }

    // Intentional code duplication
    public static boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.matches(".*@.*\\..*");
    }
}

