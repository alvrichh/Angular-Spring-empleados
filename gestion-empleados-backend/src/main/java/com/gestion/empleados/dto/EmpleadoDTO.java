package com.gestion.empleados.dto;

import com.gestion.empleados.modelo.Rol;
import java.util.Set;

public class EmpleadoDTO {

    private String usuario;
    private Set<Rol> roles;

    public EmpleadoDTO() {}


	public EmpleadoDTO(String usuario, Set<Rol> roles) {
		this.usuario = usuario;
		this.roles = roles;
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

}