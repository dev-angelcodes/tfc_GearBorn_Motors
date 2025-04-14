package com.gearborn.motors.gearbornMotors.application.controllers;

import com.gearborn.motors.gearbornMotors.application.dtos.Cliente.ClienteDTO;
import com.gearborn.motors.gearbornMotors.domain.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
