package com.gestion.empleados.modelo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

@Entity
@Table(name = "empleados")
public class Empleado implements UserDetails {
 
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

    @Column(name = "usuario", length = 60, nullable = false, unique = true)
    private String usuario;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @ElementCollection(targetClass = Rol.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "asesor", joinColumns = @JoinColumn(name = "empleado_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "roles", length = 20)
    private Set<Rol> roles = new HashSet<>();

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Añadir esta anotación
    private Set<Cliente> clientes = new HashSet<>();

    // Métodos de conveniencia para gestionar la relación bidireccional
    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
        cliente.setEmpleado(this);
    }

    public void removeCliente(Cliente cliente) {
        clientes.remove(cliente);
        cliente.setEmpleado(null);
    }
    // Getters y setters

    public Empleado(String nombre, String apellido, String email, String usuario, String password,
			Set<Rol> roles, Set<Cliente> clientes) {
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        setUsuario(usuario);
        setPassword(password);
        setRoles(roles);
        setClientes(clientes);
        }

    public Empleado() {
    	System.out.println("SE ESPERABAN DATOS DEL EMPLEADO");
    }

    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Cargar la colección de roles de manera temprana
        roles.size(); // Esto carga la colección de roles

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

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
}
