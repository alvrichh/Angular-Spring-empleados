package com.gestion.empleados.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gestion.empleados.dto.servicio.ClienteServicio;
import com.gestion.empleados.dto.servicio.EmpleadoServicio;
import com.gestion.empleados.excepciones.ResourceNotFoundException;
import com.gestion.empleados.modelo.Cliente;
import com.gestion.empleados.modelo.Empleado;

@RestController
@RequestMapping("/api/v1/empleados")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio empleadoService;

    @Autowired
    private ClienteServicio clienteService;

    // Listar todos los empleados
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Empleado> listarTodosLosEmpleados() {
        return empleadoService.listarTodosLosEmpleados();
    }

    // Guardar un empleado
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.guardarEmpleado(empleado);
    }

    // Obtener un empleado por usuario
    @GetMapping("/{usuario}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Empleado> obtenerEmpleadoPorUsuario(@PathVariable String usuario) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorUsuario(usuario)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el usuario: " + usuario));
        return ResponseEntity.ok(empleado);
    }

    // Actualizar empleado
    @PutMapping("/{usuario}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable String usuario, @RequestBody Empleado detallesEmpleado) {
        Empleado empleadoActualizado = empleadoService.actualizarEmpleado(usuario, detallesEmpleado);
        return ResponseEntity.ok(empleadoActualizado);
    }

    // Eliminar empleado
    @DeleteMapping("/{usuario}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable String usuario) {
        empleadoService.eliminarEmpleado(usuario);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    // Agregar cliente a un empleado
    @PostMapping("/{usuario}/clientes")
    @PreAuthorize("hasRole('ADMIN')")
    public Cliente agregarClienteAEmpleado(@PathVariable String usuario, @RequestBody Cliente cliente) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorUsuario(usuario)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el usuario: " + usuario));

        cliente.setEmpleado(empleado);
        return clienteService.agregarCliente(cliente);
    }

    // Listar clientes de un empleado
    @GetMapping("/{usuario}/clientes")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Cliente> listarClientesDeEmpleado(@PathVariable String usuario) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorUsuario(usuario)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el usuario: " + usuario));

        return empleado.getClientes().stream().collect(Collectors.toList());
    }
}
