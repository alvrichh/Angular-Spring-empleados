package com.gestion.empleados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.gestion.empleados.controlador.AuthControlador;
import com.gestion.empleados.dto.JwtAuthenticationResponse;
import com.gestion.empleados.dto.SigninRequest;
import com.gestion.empleados.dto.servicio.AuthenticationService;

public class AuthControllerTest {

	private  AuthControlador authcontrollerMock;
	private AuthenticationService authserviceMock;
	private JwtAuthenticationResponse jwtAuthMock;
    @Test
    public void testSignin() {
        // Mock del AuthenticationService
        AuthenticationService authcontrollerMock = mock(AuthenticationService.class);
        when(authcontrollerMock.signin(any())).thenReturn(new JwtAuthenticationResponse("token"));

        // Crear instancia del controlador
        AuthControlador controlador = new AuthControlador();
        controlador.signin((SigninRequest) authcontrollerMock);

        // Llamar al método signin
        ResponseEntity<JwtAuthenticationResponse> responseEntity = controlador.signin(new SigninRequest("username", "password"));

        // Verificar que la respuesta contiene los roles correctos
        JwtAuthenticationResponse response = responseEntity.getBody();
        assertEquals(Arrays.asList("USER", "DMIN"), response.getAccessToken());
    }
}
