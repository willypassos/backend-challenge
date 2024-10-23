package challenge_backend.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDTO {

    @Size(min = 9, message = "A senha deve ter no mínimo 9 caracteres")
    @Pattern(regexp = ".*\\d.*", message = "A senha deve conter ao menos um numero")
    @Pattern(regexp = ".*[a-z].*", message = "A senha deve conter ao menos uma letra minúscula")
    @Pattern(regexp = ".*[A-Z].*", message = "A senha deve conter ao menos uma letra maiúscula")
    @Pattern(regexp = ".*[!@#$%^&*()\\-+].*", message = "A senha deve conter ao menos um caractere especial (!@#$%^&*()-+)")
    private String password;
}
