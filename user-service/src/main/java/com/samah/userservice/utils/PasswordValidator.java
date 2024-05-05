package com.samah.userservice.utils;

public class PasswordValidator {
    private static final int MIN_LENGTH = 8;
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+";

    public static boolean isPasswordStrong(String password) {
        // Check minimum length
        if (password.length() < MIN_LENGTH) {
            return false;
        }

        // Check for at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Check for at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Check for at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Check for at least one special character
        if (!containsSpecialCharacter(password)) {
            return false;
        }

        return true;
    }

    private static boolean containsSpecialCharacter(String password) {
        for (char c : password.toCharArray()) {
            if (SPECIAL_CHARACTERS.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
}
