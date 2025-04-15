package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.GetClienteDTO;
import com.gearborn.motors.gearbornMotors.application.mappers.ClienteMapper;
import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.ClienteDTO;
import com.gearborn.motors.gearbornMotors.domain.entities.ClienteEntity;
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


    /*Guardar un nuevo Cliente, a través de un JSON*/
    public ClienteEntity save(GetClienteDTO dto){

        if(clienteRepository.findByEmail(dto.getEmail()).isPresent()) {  //Primero validamos que el Email no esta repetido
            throw new RuntimeException("Ya existe un usuario registrado con ese email");
        }else
        { //Si el email no existe, guardamos el cliente
            return clienteRepository.save(ClienteMapper.toEntity(dto));
        }
    }
}
