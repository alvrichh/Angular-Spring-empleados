package com.gestion.empleados.modelo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombre;

    private String apellidos;

    private String DNI;

    private String correo;

    private String direccion;

    private Integer telefono;
    
    private String IBAN;
    
/*
    // Relación con Asesoría (muchos clientes pueden estar asociados a una asesoría)
    @ManyToOne
    @JoinColumn(name = "idAsesoria")
    private Asesoria asesoria;

    // Relación con Contrato (un cliente puede tener varios contratos)
    @OneToMany(mappedBy = "cliente")
    private List<Suministro> suministros;
*/
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
/*
	public Asesoria getAsesoria() {
		return asesoria;
	}

	public void setAsesoria(Asesoria asesoria) {
		this.asesoria = asesoria;
	}

	public List<Suministro> getSuministros() {
		return suministros;
	}

	public void setSuministros(List<Suministro> suministros) {
		this.suministros = suministros;
	}
	*/
	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}    
}
