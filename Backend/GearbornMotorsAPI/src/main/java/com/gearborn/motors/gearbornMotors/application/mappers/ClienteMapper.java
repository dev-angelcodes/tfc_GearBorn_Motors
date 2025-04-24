package com.gearborn.motors.gearbornMotors.application.mappers;


import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.ClienteDTO;
import com.gearborn.motors.gearbornMotors.application.dtos.RegisterRequestDto;
import com.gearborn.motors.gearbornMotors.domain.entities.ClienteEntity;


public class ClienteMapper {

    //Mapeamos de un DTO a ClienteEntity
    public static ClienteEntity toEntity(RegisterRequestDto cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setDni(cliente.getDni());
        clienteEntity.setEmail(cliente.getEmail());
        clienteEntity.setContrasena(cliente.getContrasena());
        clienteEntity.setNombre(cliente.getNombre());
        clienteEntity.setApellidos(cliente.getApellidos());
        clienteEntity.setNumTelefono(cliente.getNumTelefono());
        clienteEntity.setDireccion(cliente.getDireccion());
        return clienteEntity;
    }

    //Mapeamos de ClienteEntity a un DTO
    public static ClienteDTO toDto(ClienteEntity cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setDni(cliente.getDni());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setNumTelefono(cliente.getNumTelefono());
        return clienteDTO;
    }
}
