package challenge_backend.validation;

import challenge_backend.exception.RepeatedCharacterException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class NoRepeatedCharactersValidator {
    public boolean isValid(String password) {
        Set<Character> chars = new HashSet<>();
        password.chars() //Converte o stream de inteiros (Unicode dos caracteres) para um stream de Character.
                .mapToObj(c -> (char) c)
                .filter(c -> !chars.add(c)) // : Verifica se o caractere já está no Set. Se o caractere não puder ser adicionado, ele é um repetido.
                .findFirst()
                .ifPresent(c -> { // Se o caractere é repetido, lança uma exceção.
                    throw new RepeatedCharacterException("Caractere repetido encontrado: " + c);
                });
        return true;
    }
}
