package challenge_backend.service;

import challenge_backend.exception.PasswordValidationException;
import challenge_backend.validation.PasswordValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordService {

    private final PasswordValidator passwordValidator;
    public PasswordService(PasswordValidator passwordValidator) {
        this.passwordValidator = passwordValidator;
    }

    public boolean validate(String password) {
        Optional.ofNullable(password)
                .orElseThrow(() -> new PasswordValidationException("A senha não pode ser nula."));

        boolean isValid = passwordValidator.isValid(password);

        if (!isValid) {
            throw new PasswordValidationException("A senha não atende aos critérios de validação.");
        }
        return true;
    }
}
