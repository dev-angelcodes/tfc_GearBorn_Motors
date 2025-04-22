package com.gearborn.motors.gearbornMotors.application.controllers;

import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.ClienteDTO;
import com.gearborn.motors.gearbornMotors.application.dtos.LoginRequestDto;
import com.gearborn.motors.gearbornMotors.application.dtos.RegisterRequestDto;
import com.gearborn.motors.gearbornMotors.application.mappers.ClienteMapper;
import com.gearborn.motors.gearbornMotors.domain.entities.ClienteEntity;
import com.gearborn.motors.gearbornMotors.domain.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClientController {
    private final ClienteService clienteService;

    @Autowired
    public ClientController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //Registramos un nuevo cliente
    @PostMapping("/registrarCliente")
    public ResponseEntity<?> saveCliente(@RequestBody RegisterRequestDto dto) {
        try{
            //Se pasa el cliente a la clase ClienteService para que lo guarde
            ClienteEntity cliente = clienteService.save(dto);

            //Se crea un DTO, que se pasa como respuesta a la peticion
            ClienteDTO respuesta = ClienteMapper.toDto(cliente);

            //Se devuelve la respuesta correcta, con el cliente creado
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/verificarLogin")
    public ResponseEntity<?> verificarLogin(@RequestBody LoginRequestDto dto){
        return clienteService.login(dto.getEmail(), dto.getContrasena())
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email o contraseña incorrectos"));
    }
}
