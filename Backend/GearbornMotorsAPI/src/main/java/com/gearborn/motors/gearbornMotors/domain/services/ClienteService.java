package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.RegisterRequestClientDto;
import com.gearborn.motors.gearbornMotors.application.mappers.ClienteMapper;
import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.ClienteDto;
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

    //Guardamos un cliente en la base de datos
    public ClienteEntity save(RegisterRequestClientDto dto){
        if(clienteRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario registrado con ese email");
        }else
        { //Si el email no existe, guardamos el cliente
            return clienteRepository.save(ClienteMapper.toEntity(dto));
        }
    }

    //Comprobamos si el cliente existe en la base de datos
    public Optional<ClienteDto> login(String email, String contrasenaHasheada) {
        Optional<ClienteEntity> cliente = clienteRepository.findByEmail(email);

        if (cliente.isPresent() && cliente.get().getContrasena().equals(contrasenaHasheada)) {
            ClienteDto dto = ClienteMapper.toDto(cliente.get());
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

}