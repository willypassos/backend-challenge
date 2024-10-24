package challenge_backend.unit.exceptions;

import challenge_backend.exception.PasswordValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidationExceptionTest {

    @Test
    void shouldCreateExceptionWithMessageOnly() {
        String expectedMessage = "A senha é inválida.";

        PasswordValidationException exception = new PasswordValidationException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldCreateExceptionWithMessageAndCause() {
        String expectedMessage = "A senha é inválida.";
        Throwable cause = new RuntimeException("Causa da falha");

        PasswordValidationException exception = new PasswordValidationException(expectedMessage, cause);

        assertEquals(expectedMessage, exception.getMessage());

        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldInheritFromRuntimeException() {
        String message = "A senha não atende aos critérios.";

        PasswordValidationException exception = new PasswordValidationException(message);

        assertTrue(exception instanceof RuntimeException);
    }
}
