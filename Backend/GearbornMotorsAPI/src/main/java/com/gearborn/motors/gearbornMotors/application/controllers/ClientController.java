package com.gearborn.motors.gearbornMotors.application.controllers;

import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.ClienteDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.IdClienteDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.RClientDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.RegisterRequestClientDto;
import com.gearborn.motors.gearbornMotors.application.mappers.ClienteMapper;
import com.gearborn.motors.gearbornMotors.domain.entities.ClienteEntity;
import com.gearborn.motors.gearbornMotors.domain.services.ClienteService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<?> saveCliente(@RequestBody RegisterRequestClientDto dto) {
        try{
            //Se pasa el cliente a la clase ClienteService para que lo guarde
            ClienteEntity cliente = clienteService.save(dto);

            //Se crea un DTO, que se pasa como respuesta a la peticion
            ClienteDto respuesta = ClienteMapper.toDto(cliente);

            //Se devuelve la respuesta correcta, con el cliente creado
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RClientDto dto){

        Optional<ClienteDto> cliente = clienteService.login(dto.getEmail(), dto.getContrasenaHasheada());

        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}
