package com.gestion.empleados.dto;

import java.util.List;

/**
 * Respuesta de autenticación JWT que contiene el token JWT generado.
 */
public class JwtAuthenticationResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<String> roles;

    public JwtAuthenticationResponse(String token, Long id, String username, List<String> roles) {
        setAccessToken(token);
        setId(id);
        setUsername(username);
        setRoles(roles);
    }

    // Constructor privado para el Builder
    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    // Builder para JwtAuthenticationResponse
    public static class JwtAuthenticationResponseBuilder {
        private  String token;
        private Long id;
        private String username;
        private List<String> roles;

        public JwtAuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public JwtAuthenticationResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public JwtAuthenticationResponseBuilder username(String username) {
            this.username = username;
            return this;
        }

        public JwtAuthenticationResponseBuilder roles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public JwtAuthenticationResponse build() {
            return new JwtAuthenticationResponse(token, id, username, roles);
        }
    }
}
