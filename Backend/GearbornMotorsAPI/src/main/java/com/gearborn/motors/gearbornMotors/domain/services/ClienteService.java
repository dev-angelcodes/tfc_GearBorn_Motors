package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.mappers.ClienteMapper;
import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.ClienteDTO;
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

    /*Consulta para saber un cliente por su email.*/
    public Optional<ClienteDTO> getByEmail(String email) {
        return clienteRepository.findByEmail(email)
                .map(ClienteMapper::toDto);
    }
}
