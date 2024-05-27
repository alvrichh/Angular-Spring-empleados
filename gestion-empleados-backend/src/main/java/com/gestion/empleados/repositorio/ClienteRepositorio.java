package com.gestion.empleados.repositorio;

import com.gestion.empleados.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    List<Cliente> findByEmpleadoId(Long empleadoId);
}
