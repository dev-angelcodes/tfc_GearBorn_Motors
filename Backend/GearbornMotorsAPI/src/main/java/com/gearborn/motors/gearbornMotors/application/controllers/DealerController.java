package com.gearborn.motors.gearbornMotors.application.controllers;

import com.gearborn.motors.gearbornMotors.application.dtos.Vehiculo.VehiculoDto;
import com.gearborn.motors.gearbornMotors.domain.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dealer")
public class DealerController {
    private final VehiculoService vehiculoService;

    @Autowired
    public DealerController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping("/vehiculos/total")
    public int getTotalVehiculos() {
        return this.vehiculoService.getTotalVehiculos();
    }

    @GetMapping("/vehiculos")
    public List<VehiculoDto> getAllVehiculos() {
        return this.vehiculoService.getAll();
    }
}
