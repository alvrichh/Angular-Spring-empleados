package com.gestion.empleados.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StrattonApp.Backend.DTO.ClienteDTO;
import com.StrattonApp.Backend.repository.ClienteRepository;
import com.gestion.empleados.modelo.Cliente;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository; // Asumiendo que tienes un repositorio para manejar los clientes

    private ClienteDTO mapToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getIdCliente());
        return clienteDTO;
    }
    
    public List<ClienteDTO> obtenerClientesDTO() {
        List<Cliente> clientes = clienteRepository.findAll();
        // Convertir objetos Cliente en objetos ClienteDTO
        return clientes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
  
}
