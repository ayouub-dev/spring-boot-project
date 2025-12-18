package com.emsi.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataValidatorTest {

    @Test
    void isValidPassword_WithValidPassword_ShouldReturnTrue() {
        assertTrue(DataValidator.isValidPassword("password123"));
    }

    @Test
    void isValidPassword_WithShortPassword_ShouldReturnFalse() {
        assertFalse(DataValidator.isValidPassword("pass"));
    }

    @Test
    void isValidPassword_WithNull_ShouldReturnFalse() {
        assertFalse(DataValidator.isValidPassword(null));
    }

    @Test
    void generateRandomString_ShouldReturnString() {
        String result = DataValidator.generateRandomString(10, true);
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    void validateEmail_WithValidEmail_ShouldReturnTrue() {
        assertTrue(DataValidator.validateEmail("test@example.com"));
    }

    @Test
    void validateEmail_WithInvalidEmail_ShouldReturnFalse() {
        assertFalse(DataValidator.validateEmail("invalid"));
    }

    @Test
    void validateEmail_WithNull_ShouldReturnFalse() {
        assertFalse(DataValidator.validateEmail(null));
    }
}

