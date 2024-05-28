package com.gestion.empleados.dto.servicio;

import com.gestion.empleados.modelo.Cliente;
import com.gestion.empleados.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClienteServicio {

	Cliente agregarCliente(Cliente cliente);
	Optional<Cliente> obtenerClientePorCups(String cups);
	void eliminarCliente(String cups);
}