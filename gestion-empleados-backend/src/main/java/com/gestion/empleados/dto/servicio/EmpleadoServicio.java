package com.gestion.empleados.dto.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gestion.empleados.modelo.Empleado;

public interface EmpleadoServicio {


    /**
     * Obtiene un servicio de detalles de usuario para la autenticación.
     *
     * @return Un servicio de detalles de usuario.
     */
    UserDetailsService userDetailsService();
    
    /**
     * Obtiene una lista de todos los usuarios en el sistema.
     *
     * @return Lista de objetos UsuarioDTO que representan a todos los usuarios.
     */
    List<Empleado> getAllUsers();
    
    /**
     * Obtiene un usuario por su ID.
     *
     * @param userId El ID del usuario a buscar.
     * @return Lista de usuarios encontrados (puede contener uno o ningún usuario).
     */
    List<Empleado> getUserById(Long userId);
}
