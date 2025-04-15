package com.gearborn.motors.gearbornMotors.application.controllers;

import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.ClienteDTO;
import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.GetClienteDTO;
import com.gearborn.motors.gearbornMotors.application.mappers.ClienteMapper;
import com.gearborn.motors.gearbornMotors.domain.entities.ClienteEntity;
import com.gearborn.motors.gearbornMotors.domain.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final ClienteService clienteService;

    @Autowired
    public LoginController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //Buscamos a un cliente por su email
    @GetMapping("/cliente/{email}")
    public ResponseEntity<ClienteDTO> getByEmail(@PathVariable String email) {
        return this.clienteService.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Registramos un nuevo cliente
    @PostMapping("/registrarCliente")
    public ResponseEntity<?> saveCliente(@RequestBody GetClienteDTO dto) {
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
}
