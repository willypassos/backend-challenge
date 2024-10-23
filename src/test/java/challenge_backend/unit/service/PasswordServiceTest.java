package challenge_backend.unit.service;

import challenge_backend.exception.PasswordValidationException;
import challenge_backend.service.PasswordService;
import challenge_backend.validation.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PasswordServiceTest {

    @Mock
    private PasswordValidator passwordValidator; // Mock do validador de senha

    @InjectMocks
    private PasswordService passwordService; // Injetando o mock no serviço

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTrueWhenPasswordIsValid() {
        String validPassword = "AbTp9!fok";

        when(passwordValidator.isValid(validPassword)).thenReturn(true);

        boolean result = passwordService.validate(validPassword);

        assertTrue(result);

        verify(passwordValidator, times(1)).isValid(validPassword);
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsInvalid() {
        String invalidPassword = "abc";

        when(passwordValidator.isValid(invalidPassword)).thenReturn(false);

        PasswordValidationException exception = assertThrows(
                PasswordValidationException.class,
                () -> passwordService.validate(invalidPassword)
        );

        assertEquals("A senha não atende aos critérios de validação.", exception.getMessage());

        verify(passwordValidator, times(1)).isValid(invalidPassword);
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNull() {
        String nullPassword = null;

        PasswordValidationException exception = assertThrows(
                PasswordValidationException.class,
                () -> passwordService.validate(nullPassword)
        );

        assertEquals("A senha não pode ser nula.", exception.getMessage());

        verify(passwordValidator, never()).isValid(nullPassword);
    }
}
