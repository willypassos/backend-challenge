package challenge_backend.unit.controller;

import challenge_backend.controller.PasswordController;
import challenge_backend.service.PasswordService;
import challenge_backend.dto.PasswordDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PasswordControllerTest {

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private PasswordController passwordController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnOkWhenPasswordIsValid() {
        PasswordDTO validPasswordDTO = new PasswordDTO("AbTp9!fok");

        when(passwordService.validate(validPasswordDTO.getPassword())).thenReturn(true);

        ResponseEntity<Boolean> response = passwordController.validatePassword(validPasswordDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());

        verify(passwordService, times(1)).validate(validPasswordDTO.getPassword());
    }
}
