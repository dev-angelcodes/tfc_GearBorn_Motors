package com.gearborn.motors.gearbornMotors.application.mappers;


import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.ClienteDTO;
import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.GetClienteDTO;
import com.gearborn.motors.gearbornMotors.domain.entities.ClienteEntity;

public class ClienteMapper {

    //Mapeamos de un DTO a ClienteEntity
    public static ClienteEntity toEntity(GetClienteDTO cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setDni(cliente.getDni());
        clienteEntity.setEmail(cliente.getEmail());
        clienteEntity.setContrasena(cliente.getContrasena());
        clienteEntity.setNombre(cliente.getNombre());
        clienteEntity.setApellidos(cliente.getApellidos());
        clienteEntity.setNumTelefono(cliente.getNumTelefono());
        clienteEntity.setFechaNacimiento(cliente.getFechaNacimiento());
        clienteEntity.setDireccion(cliente.getDireccion());
        return clienteEntity;
    }

    //Mapeamos de ClienteEntity a un DTO
    public static ClienteDTO toDto(ClienteEntity cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setEmail(cliente.getEmail());
        return clienteDTO;
    }
}
