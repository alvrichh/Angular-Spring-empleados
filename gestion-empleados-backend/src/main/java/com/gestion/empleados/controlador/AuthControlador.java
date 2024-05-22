package com.gestion.empleados.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.gestion.empleados.dto.JwtAuthenticationResponse;
import com.gestion.empleados.dto.SignUpRequest;
import com.gestion.empleados.dto.SigninRequest;
import com.gestion.empleados.dto.servicio.AuthenticationService;

import lombok.RequiredArgsConstructor;

/**
 * Controlador para gestionar las operaciones de autenticación de usuarios.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin // Permite el acceso CORS de cualquier origen a todos los endpoints en este controlador
public class AuthControlador {

    @Autowired
    private  AuthenticationService authenticationService;

    /**
     * Registra un nuevo usuario.
     *
     * @param request Datos de registro del usuario.
     * @return ResponseEntity con la respuesta de autenticación JWT.
     */
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    /**
     * Inicia sesión con credenciales de usuario.
     *
     * @param request Datos de inicio de sesión.
     * @return ResponseEntity con la respuesta de autenticación JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody SigninRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado o credenciales incorrectas");
        }
    }
}