package com.gestion.empleados.dto;

import com.gestion.empleados.modelo.Rol;

/**
 * Clase que representa la solicitud de inicio de sesión de un usuario.
 */
public class SigninRequest {

    private String username;

    private String password;

    private Rol roles;
    
    
    public SigninRequest() {
    }

    public SigninRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        this.setRoles(roles);
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

	public Rol getRoles() {
		return roles;
	}

	public void setRoles(Rol roles) {
		this.roles = roles;
	}
}
