package challenge_backend.unit.exceptions;

import challenge_backend.exception.PasswordLengthException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordLengthExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        String expectedMessage = "A senha deve ter no mínimo 9 caracteres.";

        PasswordLengthException exception = new PasswordLengthException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldInheritFromRuntimeException() {
        String message = "A senha é muito curta.";

        PasswordLengthException exception = new PasswordLengthException(message);

        assertTrue(exception instanceof RuntimeException);
    }
    @Test
    void shouldCreateExceptionWithMessageOnly() {
        String expectedMessage = "A senha deve ter no mínimo 9 caracteres.";

        PasswordLengthException exception = new PasswordLengthException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldCreateExceptionWithMessageAndCause() {
        String expectedMessage = "A senha deve ter no mínimo 9 caracteres.";
        Throwable cause = new RuntimeException("Causa da falha");

        PasswordLengthException exception = new PasswordLengthException(expectedMessage, cause);

        assertEquals(expectedMessage, exception.getMessage());

        assertEquals(cause, exception.getCause());
    }
}
