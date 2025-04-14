package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.ClienteDTO;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //Consulta para saber un cliente por su id, para saber si existe o no en la base de datos
    public Optional<ClienteDTO> getByEmail(String email) {
        return clienteRepository.findByEmail(email)
                .map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNombre(),
                                                cliente.getApellidos(), cliente.getEmail()));
    }
}
