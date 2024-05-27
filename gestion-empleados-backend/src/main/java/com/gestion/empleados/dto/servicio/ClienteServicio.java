package com.gestion.empleados.dto.servicio;

import com.gestion.empleados.modelo.Cliente;
import com.gestion.empleados.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public Cliente registrarCliente(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepositorio.findAll();
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

    public void eliminarCliente(Long id) {
        clienteRepositorio.deleteById(id);
    }

    public List<Cliente> obtenerClientesPorEmpleado(Long empleadoId) {
        return clienteRepositorio.findByEmpleadoId(empleadoId);
    }
}
