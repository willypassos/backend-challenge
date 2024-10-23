package challenge_backend.integration.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnBadRequestForShortPassword() throws Exception {
        String invalidPasswordJson = "{\"password\": \"abcJ1&\"}"; // Menos de 9 caracteres
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPasswordJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("A senha deve ter no mínimo 9 caracteres"));
    }

    @Test
    public void shouldReturnBadRequestForPasswordWithoutDigit() throws Exception {
        String invalidPasswordJson = "{\"password\": \"abcdefghA!\"}"; // Sem dígitos
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPasswordJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("A senha deve conter ao menos um numero"));
    }

    @Test
    public void shouldReturnBadRequestForPasswordWithoutLowerCase() throws Exception {
        String invalidPasswordJson = "{\"password\": \"ABCDEFGH1!\"}"; // Sem letra minúscula
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPasswordJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("A senha deve conter ao menos uma letra minúscula"));
    }

    @Test
    public void shouldReturnBadRequestForPasswordWithoutUpperCase() throws Exception {
        String invalidPasswordJson = "{\"password\": \"abcdefgh1!\"}"; // Sem letra maiúscula
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPasswordJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("A senha deve conter ao menos uma letra maiúscula"));
    }

    @Test
    public void shouldReturnBadRequestForPasswordWithoutSpecialCharacter() throws Exception {
        String invalidPasswordJson = "{\"password\": \"Abcdefgh1\"}"; // Sem caractere especial
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPasswordJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("A senha deve conter ao menos um caractere especial (!@#$%^&*()-+)"));
    }

    @Test
    public void shouldReturnBadRequestForPasswordWithRepeatedCharacters() throws Exception {
        String invalidPasswordJson = "{\"password\": \"Abcdefgh1!!\"}"; // Caracteres repetidos
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPasswordJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Caractere repetido encontrado: !"));
    }

    @Test
    public void shouldReturnOkForValidPassword() throws Exception {
        // Cria uma senha válida
        String validPasswordJson = "{\"password\": \"Abcdef123!\"}";

        // Simula uma requisição POST e verifica se a resposta é 200 OK e o corpo da resposta é true
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPasswordJson))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
