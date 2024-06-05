package com.gestion.empleados.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.empleados.modelo.Empleado;
import java.util.Set;
import com.gestion.empleados.modelo.Cliente;


@Repository

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long>{

	
	
	Optional<Empleado> findByUsuario(String Susuario);
    Boolean existsByEmail(String email);
    long countByClientes(Set<Cliente> clientes);
	Empleado findByUsername(String username);
    

    
}
