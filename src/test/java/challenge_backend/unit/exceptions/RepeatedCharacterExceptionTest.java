package challenge_backend.unit.exceptions;

import challenge_backend.exception.RepeatedCharacterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepeatedCharacterExceptionTest {

    @Test
    void shouldCreateExceptionWithMessageOnly() {
        String expectedMessage = "Caractere repetido encontrado: a";

        RepeatedCharacterException exception = new RepeatedCharacterException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldCreateExceptionWithMessageAndCause() {
        String expectedMessage = "Caractere repetido encontrado: a";
        Throwable cause = new RuntimeException("Causa da falha");

        RepeatedCharacterException exception = new RepeatedCharacterException(expectedMessage, cause);

        assertEquals(expectedMessage, exception.getMessage());

        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldInheritFromRuntimeException() {
        String message = "Erro no caractere repetido.";

        RepeatedCharacterException exception = new RepeatedCharacterException(message);

        assertTrue(exception instanceof RuntimeException);
    }
}
