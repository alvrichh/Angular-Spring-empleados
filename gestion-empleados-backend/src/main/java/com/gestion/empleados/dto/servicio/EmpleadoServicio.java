package com.gestion.empleados.dto.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gestion.empleados.dto.EmpleadoDTO;
import com.gestion.empleados.modelo.Cliente;
import com.gestion.empleados.modelo.Empleado;

public interface EmpleadoServicio {
    List<Empleado> listarTodosLosEmpleados();
    Empleado guardarEmpleado(Empleado empleado);
    Optional<Empleado> obtenerEmpleadoPorUsuario(String usuario);
    Empleado actualizarEmpleado(String usuario, Empleado empleadoDetalles);
    void eliminarEmpleado(String usuario);
	UserDetailsService userDetailsService();
	List<Cliente> listarClientesDeEmpleado(String usuario);
}