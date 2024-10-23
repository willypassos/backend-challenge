package challenge_backend.exception;

public class PasswordLengthException extends RuntimeException {
    public PasswordLengthException(String message) {
        super(message);
    }
    public PasswordLengthException(String message, Throwable cause) {
        super(message, cause);
    }
}
