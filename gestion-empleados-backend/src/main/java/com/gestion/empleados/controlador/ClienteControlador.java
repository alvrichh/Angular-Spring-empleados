package com.gestion.empleados.controlador;

import com.gestion.empleados.excepciones.ResourceNotFoundException;
import com.gestion.empleados.modelo.Cliente;
import com.gestion.empleados.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    // Método para obtener todos los clientes
    @GetMapping("/clientes")
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepositorio.findAll();
    }

    // Método para guardar un nuevo cliente
    @PostMapping("/clientes")
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    // Método para obtener un cliente por su ID
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el cliente con el ID : " + id));
        return ResponseEntity.ok(cliente);
    }

    // Método para actualizar un cliente
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente detallesCliente) {
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el cliente con el ID : " + id));
        
        cliente.setNombre(detallesCliente.getNombre());
        cliente.setApellidos(detallesCliente.getApellidos());
        cliente.setDni(detallesCliente.getDni());
        cliente.setEmail(detallesCliente.getEmail());
        cliente.setComercializadora(detallesCliente.getComercializadora());
        cliente.setNumeroCUPS(detallesCliente.getNumeroCUPS());
        cliente.setTelefono(detallesCliente.getTelefono());
        
        Cliente clienteActualizado = clienteRepositorio.save(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    // Método para eliminar un cliente
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el cliente con el ID : " + id));
        
        clienteRepositorio.delete(cliente);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    // Método para obtener los clientes asociados a un empleado específico
    @GetMapping("/empleados/{empleadoId}/clientes")
    public List<Cliente> obtenerClientesPorEmpleado(@PathVariable Long empleadoId) {
        return clienteRepositorio.findByEmpleadoId(empleadoId);
    }
}
