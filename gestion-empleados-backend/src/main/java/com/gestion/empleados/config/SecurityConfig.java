package com.gestion.empleados.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.gestion.empleados.dto.servicio.EmpleadoServicio;
import com.gestion.empleados.modelo.Rol;

import lombok.RequiredArgsConstructor;

/**
 * Configuración de seguridad para la aplicación.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    EmpleadoServicio userService;

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http Configuración de seguridad HTTP.
     * @return La cadena de filtros de seguridad configurada.
     * @throws Exception Si ocurre un error al configurar la seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->           
                request
                .requestMatchers("/api/v1/auth/**").permitAll()
                //GESTION EMPLEADOS
                .requestMatchers(HttpMethod.GET, "/api/v1/empleados/**").hasAuthority(Rol.ADMIN.toString())
                .requestMatchers(HttpMethod.POST, "/api/v1/empleados/**").hasAuthority(Rol.ADMIN.toString())
                .requestMatchers(HttpMethod.DELETE, "/api/v1/empleados/**").hasAuthority(Rol.ADMIN.toString())
                
                //GESTION CLIENTES
                .requestMatchers(HttpMethod.GET, "/api/v1/clientes/**").hasAnyAuthority(Rol.USER.toString(), Rol.ADMIN.toString())
                .requestMatchers(HttpMethod.POST, "/api/v1/clientes/**").hasAnyAuthority(Rol.USER.toString(), Rol.ADMIN.toString())
                .requestMatchers(HttpMethod.DELETE, "/api/v1/clientes/**").hasAnyAuthority(Rol.USER.toString(), Rol.ADMIN.toString())
                .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200"); // Reemplaza con el origen de tu frontend
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}