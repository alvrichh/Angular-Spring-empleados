package com.gestion.empleados.repositorio;

import com.gestion.empleados.modelo.Cliente;
import com.gestion.empleados.modelo.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    List<Cliente> findByEmpleadoId(Long empleadoId);

    Optional<Cliente> findByNumeroCUPS(String numeroCUPS);

	List<Cliente> findByEmpleado(Optional<Empleado> empleado);

}
