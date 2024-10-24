package challenge_backend.unit.exceptions;

import challenge_backend.exception.GlobalExceptionHandler;
import challenge_backend.exception.PasswordLengthException;
import challenge_backend.exception.RepeatedCharacterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void shouldHandleRepeatedCharacterException() {
        RepeatedCharacterException exception = new RepeatedCharacterException("Caractere repetido encontrado: a");

        ResponseEntity<String> response = globalExceptionHandler.handleRepeatedCharacterException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Caractere repetido encontrado: a", response.getBody());
    }

    @Test
    void shouldHandlePasswordLengthException() {
        PasswordLengthException exception = new PasswordLengthException("A senha deve ter no mínimo 9 caracteres.");

        ResponseEntity<String> response = globalExceptionHandler.handlePasswordLengthException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A senha deve ter no mínimo 9 caracteres.", response.getBody());
    }

    @Test
    void shouldHandleValidationException() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("passwordDTO", "password", "A senha é inválida.");
        when(bindingResult.getFieldError()).thenReturn(fieldError);

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<String> response = globalExceptionHandler.handleValidationException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A senha é inválida.", response.getBody());
    }

    @Test
    void shouldHandleGenericException() {
        Exception exception = new Exception("Erro inesperado.");

        ResponseEntity<String> response = globalExceptionHandler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno no servidor", response.getBody());
    }
}
