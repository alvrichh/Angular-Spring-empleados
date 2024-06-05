//package com.gestion.empleados.dto.servicio;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.gestion.empleados.dto.JwtAuthenticationResponse;
//import com.gestion.empleados.dto.SignUpRequest;
//import com.gestion.empleados.dto.SigninRequest;
//import com.gestion.empleados.modelo.Empleado;
//import com.gestion.empleados.repositorio.EmpleadoRepositorio;
//import com.gestion.empleados.dto.JwtAuthenticationResponse.JwtAuthenticationResponseBuilder;
//
//import lombok.Builder;
//
///**
// * Implementación del servicio AuthenticationService para manejar operaciones de autenticación.
// */
//@Service
//public class AuthenticationServiceImpl implements AuthenticationService {
//
//    private final EmpleadoRepositorio empleadorepositorio; // Asegúrate de que EmpleadoRepositorio esté inyectado correctamente
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    // Constructor para inyección de dependencias
//    public AuthenticationServiceImpl(EmpleadoRepositorio empleadorepositorio,
//                                     PasswordEncoder passwordEncoder,
//                                     JwtService jwtService,
//                                     AuthenticationManager authenticationManager) {
//        this.empleadorepositorio = empleadorepositorio;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//    }
//
//    /**
//     * Registra un nuevo usuario en el sistema.
//     *
//     * @param request Detalles de registro del usuario.
//     * @return Respuesta de autenticación con el token JWT.
//     * @throws IllegalArgumentException Si el correo electrónico ya está en uso.
//     */
//    @Override
//    public JwtAuthenticationResponse signup(SignUpRequest request) {
//        if (empleadorepositorio.existsByEmail(request.getEmail())) {
//            throw new IllegalArgumentException("Email already in use.");
//        }
//
//        // Corrige la forma de construir el objeto 'User'
//        Empleado user = new Empleado();
//        user.setUsuario(request.getUsername());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        empleadorepositorio.save(user);
//
//        String jwt = jwtService.generateToken(user);
//        return JwtAuthenticationResponse.builder().token(jwt).build();
//    }
//
//    /**
//     * Inicia sesión de un usuario existente en el sistema.
//     *
//     * @param request Detalles de inicio de sesión del usuario.
//     * @return Respuesta de autenticación con el token JWT.
//     * @throws IllegalArgumentException Si el nombre de usuario o la contraseña son inválidos.
//     */
//    @Override
//    public JwtAuthenticationResponse signin(SigninRequest request) {
//        Empleado user = empleadorepositorio.findByUsuario(request.getUsername())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
//
//        String jwt = jwtService.generateToken(user);
//        return JwtAuthenticationResponse.JwtAuthenticationResponseBuilder().setAccessToken(jwt);
//    }
//}
