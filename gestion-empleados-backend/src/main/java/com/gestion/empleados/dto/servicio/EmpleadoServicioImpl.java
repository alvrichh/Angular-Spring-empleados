package com.gestion.empleados.dto.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion.empleados.dto.EmpleadoDTO;
import com.gestion.empleados.excepciones.ResourceNotFoundException;
import com.gestion.empleados.modelo.Empleado;
import com.gestion.empleados.repositorio.EmpleadoRepositorio;

@Service
public class EmpleadoServicioImpl implements EmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Empleado> listarTodosLosEmpleados() {
        return empleadoRepositorio.findAll();
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        return empleadoRepositorio.save(empleado);
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoPorUsuario(String usuario) {
        return empleadoRepositorio.findByUsuario(usuario);
    }

    @Override
    public Empleado actualizarEmpleado(String usuario, Empleado empleadoDetalles) {
        Empleado empleado = empleadoRepositorio.findByUsuario(usuario)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el usuario: " + usuario));

        empleado.setNombre(empleadoDetalles.getNombre());
        empleado.setApellido(empleadoDetalles.getApellido());
        empleado.setEmail(empleadoDetalles.getEmail());
        empleado.setRoles(empleadoDetalles.getRoles());
        empleado.setPassword(passwordEncoder.encode(empleadoDetalles.getPassword()));

        return empleadoRepositorio.save(empleado);
    }

    @Override
    public void eliminarEmpleado(String usuario) {
        Empleado empleado = empleadoRepositorio.findByUsuario(usuario)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el usuario: " + usuario));
        empleadoRepositorio.delete(empleado);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return empleadoRepositorio.findByUsuario(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}