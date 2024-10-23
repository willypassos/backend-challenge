package challenge_backend.unit.validations;

import challenge_backend.validation.NoRepeatedCharactersValidator;
import challenge_backend.exception.RepeatedCharacterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoRepeatedCharactersValidatorTest {

    private NoRepeatedCharactersValidator validator;

    @BeforeEach
    void setUp() {
        validator = new NoRepeatedCharactersValidator(); // Inicializa a instância do validador
    }

    @Test
    void shouldReturnTrueWhenNoRepeatedCharacters() {
        String validPassword = "AbTp9!fok";

        boolean result = validator.isValid(validPassword);

        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenRepeatedCharacterFound() {
        String invalidPassword = "AbTp9!foo"; // 'o' repetido

        RepeatedCharacterException exception = assertThrows(
                RepeatedCharacterException.class,
                () -> validator.isValid(invalidPassword)
        );

        assertEquals("Caractere repetido encontrado: o", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAllCharactersAreRepeated() {
        String invalidPassword = "aaaaaaa";

        RepeatedCharacterException exception = assertThrows(
                RepeatedCharacterException.class,
                () -> validator.isValid(invalidPassword)
        );

        assertEquals("Caractere repetido encontrado: a", exception.getMessage());
    }
}
