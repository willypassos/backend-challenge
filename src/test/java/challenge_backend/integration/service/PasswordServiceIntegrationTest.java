package challenge_backend.integration.service;

import challenge_backend.exception.PasswordValidationException;
import challenge_backend.service.PasswordService;
import challenge_backend.validation.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PasswordServiceIntegrationTest {

    @Autowired
    private PasswordService passwordService;

    @MockBean
    private PasswordValidator passwordValidator;

    @BeforeEach
    void setUp() {
        when(passwordValidator.isValid("ValidPassword123!")).thenReturn(true);
        when(passwordValidator.isValid("invalid")).thenReturn(false);
    }

    @Test
    void shouldReturnTrueForValidPassword() {
        boolean result = passwordService.validate("ValidPassword123!");
        assertTrue(result);
    }

    @Test
    void shouldThrowPasswordValidationExceptionForInvalidPassword() {
        assertThrows(PasswordValidationException.class, () -> {
            passwordService.validate("invalid");
        });
    }

    @Test
    void shouldThrowPasswordValidationExceptionForNullPassword() {
        assertThrows(PasswordValidationException.class, () -> {
            passwordService.validate(null);
        });
    }
}
