package com.gestion.empleados.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.gestion.empleados.config.JwtUtils;
import com.gestion.empleados.dto.JwtAuthenticationResponse;
import com.gestion.empleados.dto.JwtResponse;
import com.gestion.empleados.dto.SignUpRequest; //posible actualizacion
import com.gestion.empleados.dto.SigninRequest;
import com.gestion.empleados.dto.UsuarioResponse;
import com.gestion.empleados.dto.servicio.AuthenticationService;
import com.gestion.empleados.dto.servicio.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 * Controlador para gestionar las operaciones de autenticación de los empleados de la asesoría.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin // Permite el acceso CORS de cualquier origen a todos los endpoints en este controlador
public class AuthControlador {
	
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  AuthenticationService authenticationService;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
    	
        return ResponseEntity.ok(authenticationService.signin(request));
    
    }
    
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponse> miPerfil(Authentication authentication) {
        // Asume que el principal es un objeto UserDetails o similar
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(403).build();
        }

        // Obtener detalles del usuario autenticado
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        String firstName = ""; // Obtener del servicio o base de datos
        String lastName = "";  // Obtener del servicio o base de datos
        String email = principal.getUsername(); // Asumiendo que el username es el email
        String roles = principal.getAuthorities().toString();

        UsuarioResponse userResponse = new UsuarioResponse(firstName, lastName, email, roles);

        return ResponseEntity.ok(userResponse);
    }
    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody SigninRequest jwtRequest) throws Exception {
        try{
            autenticar(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (Exception exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }
    // TODO PAGINA DEL PERFIL DEL EMPLEADO // PERSONALIZACIÓN DEL PERFIL
}