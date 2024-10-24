package challenge_backend.exception;

public class RepeatedCharacterException extends RuntimeException {
    public RepeatedCharacterException(String message) {
        super(message);
    }
    public RepeatedCharacterException(String message, Throwable cause) {
        super(message, cause);
    }
}
