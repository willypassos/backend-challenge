package challenge_backend.validation;

import challenge_backend.exception.PasswordLengthException;
import challenge_backend.exception.RepeatedCharacterException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class PasswordValidator {

    private static final int MIN_PASSWORD_LENGTH = 9;

    public boolean isValid(String password) {
        Optional.ofNullable(password)
                .filter(p -> p.length() >= MIN_PASSWORD_LENGTH)
                .orElseThrow(() -> new PasswordLengthException("A senha deve ter no mínimo " + MIN_PASSWORD_LENGTH + " caracteres."));

        validatePasswordConditions(password, this::containsDigit, "A senha deve conter ao menos um dígito.");
        validatePasswordConditions(password, this::containsLowerCase, "A senha deve conter ao menos uma letra minúscula.");
        validatePasswordConditions(password, this::containsUpperCase, "A senha deve conter ao menos uma letra maiúscula.");
        validatePasswordConditions(password, this::containsSpecialCharacter, "A senha deve conter ao menos um caractere especial (!@#$%^&*()-+).");

        checkForRepeatedCharacters(password);

        return true;
    }

    private void validatePasswordConditions(String password, Predicate<String> condition, String errorMessage) {
        Optional.of(password)
                .filter(condition)
                .orElseThrow(() -> new PasswordLengthException(errorMessage));
    }

    private void checkForRepeatedCharacters(String password) {
        Set<Character> chars = new HashSet<>();
        password.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !chars.add(c)) // Se o caractere já estiver no Set, é repetido
                .findFirst()
                .ifPresent(c -> {
                    throw new RepeatedCharacterException("Caractere repetido encontrado: " + c);
                });
    }

    private boolean containsDigit(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }

    private boolean containsLowerCase(String password) {
        return password.chars().anyMatch(Character::isLowerCase);
    }

    private boolean containsUpperCase(String password) {
        return password.chars().anyMatch(Character::isUpperCase);
    }

    private boolean containsSpecialCharacter(String password) {
        String specialChars = "!@#$%^&*()-+";
        return password.chars().anyMatch(c -> specialChars.indexOf(c) >= 0);
    }
}
