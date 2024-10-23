package challenge_backend.controller;

import challenge_backend.service.PasswordService;
import challenge_backend.dto.PasswordDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password")
public class PasswordController {


    private PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validatePassword(@Valid @RequestBody PasswordDTO passwordDTO) {
        boolean isValid = passwordService.validate(passwordDTO.getPassword());
        return ResponseEntity.ok(true); // Senha válida
    }
}
