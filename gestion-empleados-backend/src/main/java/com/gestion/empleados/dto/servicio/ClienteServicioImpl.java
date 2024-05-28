package com.gestion.empleados.dto.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.empleados.excepciones.ResourceNotFoundException;
import com.gestion.empleados.modelo.Cliente;
import com.gestion.empleados.repositorio.ClienteRepositorio;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Override
    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @Override
    public Optional<Cliente> obtenerClientePorCups(String numeroCUPS) {
        return clienteRepositorio.findByNumeroCUPS(numeroCUPS);
    }

    @Override
    public void eliminarCliente(String numeroCUPS) {
        Cliente cliente = clienteRepositorio.findByNumeroCUPS(numeroCUPS)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el cliente con el CUPS: " + numeroCUPS));
        clienteRepositorio.delete(cliente);
    }
}