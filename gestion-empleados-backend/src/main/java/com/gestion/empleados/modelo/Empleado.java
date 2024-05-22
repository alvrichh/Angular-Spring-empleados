package com.gestion.empleados.modelo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

@Entity
@Table(name = "empleados")
public class Empleado implements UserDetails  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", length = 60, nullable = false)
	private String nombre;

	@Column(name = "apellido", length = 60, nullable = false)
	private String apellido;

	@Column(name = "email", length = 60, nullable = false, unique = true)
	private String email;
	
    @Column(name= "usuario", unique = true)
    private String username;

    @Column(name= "password")
	private String password;
/*
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "usuario_rol")
    @Column(name = "RolesUsuario")
    private Set<Role> roles = new HashSet<>();
	*/
	public Empleado() {

	}

	public Empleado(Long id, String nombre, String apellido, String email, String username, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.username= username;
		this.password= password;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
		this.username = username;
	}
    
    @Override
    public String getPassword() {
        return password;
    }
	public void setPassword(String password) {
		this.password = password;
	}
    
    	/**
    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Cargar la colección de roles de manera temprana
        roles.size(); // Esto carga la colección de roles

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
    
    
	**/
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

  
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }




	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	/**
    public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
**/

}
