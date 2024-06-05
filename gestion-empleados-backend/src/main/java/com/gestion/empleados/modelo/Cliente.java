package com.gestion.empleados.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "clientes")
public class Cliente {
    private static  long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", length = 60, nullable = false)
    private String nombre;
    
    @Column(name = "apellido", length = 60, nullable = false)
    private String apellidos;
    
    @Column(name = "dni", length = 60, nullable = false)
    private String dni;
    
    @Column(name = "email", length = 60, nullable = false, unique = true)
    private String email;
    
    @Column(name = "comercializadora", length = 60, nullable = false)
    private String comercializadora;
    
    @Column(name = "numeroCUPS", length = 60, nullable = false, unique = true)
    private String numeroCUPS;
    
    @Column(name = "telefono", length = 60, nullable = false)
    private String telefono;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference // Añadir esta anotación
    @JoinColumn(name = "asesor")
    private Empleado empleado;

    

    // Getters y setters

    public Cliente(String nombre, String apellidos, String dni, String email, String comercializadora,
			String numeroCUPS, String telefono, Empleado empleado) {
		setNombre(nombre);
        setApellidos(apellidos);
        setDni(dni);
        setEmail(email);
        setComercializadora(comercializadora);
        setNumeroCUPS(numeroCUPS);
        setNumeroCUPS(numeroCUPS);
        setEmpleado(empleado);
	}
    public Cliente() {}

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComercializadora() {
        return comercializadora;
    }

    public void setComercializadora(String comercializadora) {
        this.comercializadora = comercializadora;
    }

    public String getNumeroCUPS() {
        return numeroCUPS;
    }

    public void setNumeroCUPS(String numeroCUPS) {
        this.numeroCUPS = numeroCUPS;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
