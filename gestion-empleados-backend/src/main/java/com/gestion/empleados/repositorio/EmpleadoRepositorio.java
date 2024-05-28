package com.gestion.empleados.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.empleados.modelo.Empleado;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long>{

	Optional<Empleado> findByUsuario(String usuario);
    Boolean existsByEmail(String email);
    
}
