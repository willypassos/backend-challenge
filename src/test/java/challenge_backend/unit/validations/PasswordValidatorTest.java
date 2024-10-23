package challenge_backend.unit.validations;

import challenge_backend.exception.PasswordLengthException;
import challenge_backend.exception.RepeatedCharacterException;
import challenge_backend.validation.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    private PasswordValidator passwordValidator;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    void shouldThrowExceptionForShortPassword() {
        String shortPassword = "Abc1!";
        PasswordLengthException exception = assertThrows(PasswordLengthException.class,
                () -> passwordValidator.isValid(shortPassword));
        assertEquals("A senha deve ter no mínimo 9 caracteres.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForPasswordWithoutDigit() {
        String passwordWithoutDigit = "Abcdefghi!";
        PasswordLengthException exception = assertThrows(PasswordLengthException.class,
                () -> passwordValidator.isValid(passwordWithoutDigit));
        assertEquals("A senha deve conter ao menos um dígito.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForPasswordWithoutLowerCase() {
        String passwordWithoutLowerCase = "ABCDEFGHI1!";
        PasswordLengthException exception = assertThrows(PasswordLengthException.class,
                () -> passwordValidator.isValid(passwordWithoutLowerCase));
        assertEquals("A senha deve conter ao menos uma letra minúscula.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForPasswordWithoutUpperCase() {
        String passwordWithoutUpperCase = "abcdefghi1!";
        PasswordLengthException exception = assertThrows(PasswordLengthException.class,
                () -> passwordValidator.isValid(passwordWithoutUpperCase));
        assertEquals("A senha deve conter ao menos uma letra maiúscula.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForPasswordWithoutSpecialCharacter() {
        String passwordWithoutSpecialCharacter = "Abcdefghi1";
        PasswordLengthException exception = assertThrows(PasswordLengthException.class,
                () -> passwordValidator.isValid(passwordWithoutSpecialCharacter));
        assertEquals("A senha deve conter ao menos um caractere especial (!@#$%^&*()-+).", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForPasswordWithRepeatedCharacters() {
        String passwordWithRepeatedCharacters = "Abcdefghi1!!";
        RepeatedCharacterException exception = assertThrows(RepeatedCharacterException.class,
                () -> passwordValidator.isValid(passwordWithRepeatedCharacters));
        assertEquals("Caractere repetido encontrado: !", exception.getMessage());
    }

    @Test
    void shouldReturnTrueForValidPassword() {
        String validPassword = "Abcdefghi1!";
        assertTrue(passwordValidator.isValid(validPassword));
    }
}
