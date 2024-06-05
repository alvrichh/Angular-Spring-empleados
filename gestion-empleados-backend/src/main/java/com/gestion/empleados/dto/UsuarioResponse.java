package com.gestion.empleados.dto;

/**
 * Clase que representa la respuesta del perfil del usuario.
 */
public class UsuarioResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String roles;

    public UsuarioResponse(String firstName, String lastName, String email, String roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    // Getters y Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
