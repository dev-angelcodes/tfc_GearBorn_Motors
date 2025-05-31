package com.gearborn.motors.gearbornMotors.application.controllers;

import com.gearborn.motors.gearbornMotors.application.dtos.Venta.VentaDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Venta.VentaRequestDto;
import com.gearborn.motors.gearbornMotors.domain.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaController {
    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping("/crearVenta")
    public ResponseEntity<Void> crearVenta(@RequestBody VentaRequestDto ventaRequestDto) {
        ventaService.save(ventaRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getVentas")
    public List<VentaDto> getAllVentas() {
        return ventaService.getAll();
    }
}
