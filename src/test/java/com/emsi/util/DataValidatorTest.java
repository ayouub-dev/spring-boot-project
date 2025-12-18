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
        String result = DataValidator.generateRandomString(10);
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    void isValidEmail_WithValidEmail_ShouldReturnTrue() {
        assertTrue(DataValidator.isValidEmail("test@example.com"));
    }

    @Test
    void isValidEmail_WithInvalidEmail_ShouldReturnFalse() {
        assertFalse(DataValidator.isValidEmail("invalid"));
    }

    @Test
    void isValidEmail_WithNull_ShouldReturnFalse() {
        assertFalse(DataValidator.isValidEmail(null));
    }

    @Test
    void isValidName_WithValidName_ShouldReturnTrue() {
        assertTrue(DataValidator.isValidName("John Doe"));
    }

    @Test
    void isValidName_WithNull_ShouldReturnFalse() {
        assertFalse(DataValidator.isValidName(null));
    }

    @Test
    void isValidName_WithEmptyString_ShouldReturnFalse() {
        assertFalse(DataValidator.isValidName("   "));
    }
}

