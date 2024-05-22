package com.gestion.empleados.dto.servicio;

import org.springframework.stereotype.Service;

import com.gestion.empleados.dto.JwtAuthenticationResponse;
import com.gestion.empleados.dto.SignUpRequest;
import com.gestion.empleados.dto.SigninRequest;

/**
 * Interfaz que define los métodos para el servicio de autenticación y autorización.
 */
@Service
public interface AuthenticationService {

    /**
     * Realiza el registro de un nuevo usuario y proporciona un token JWT en respuesta.
     *
     * @param request Datos de registro del usuario.
     * @return Respuesta de autenticación que incluye el token JWT.
     */
    JwtAuthenticationResponse signup(SignUpRequest request);

    /**
     * Realiza el inicio de sesión y proporciona un token JWT en respuesta.
     *
     * @param request Datos de inicio de sesión del usuario.
     * @return Respuesta de autenticación que incluye el token JWT.
     */
    JwtAuthenticationResponse signin(SigninRequest request);
}
