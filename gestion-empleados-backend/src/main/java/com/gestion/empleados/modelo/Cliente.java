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
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String comercializadora;
    @Column(name = "numeroCUPS", length = 60, nullable = false, unique = true)
    private String numeroCUPS;
    private String telefono;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id")
    @JsonBackReference // Añadir esta anotación
    private Empleado empleado;

    

    // Getters y setters

    public Cliente(Long id, String nombre, String apellidos, String dni, String email, String comercializadora,
			String numeroCUPS, String telefono, Empleado empleado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.comercializadora = comercializadora;
		this.numeroCUPS = numeroCUPS;
		this.telefono = telefono;
		this.empleado = empleado;
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
