package com.gearborn.motors.gearbornMotors.application.controllers;

import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.CompraGastoRequestDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.GastoDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.GastoFrontDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Gastos.GastoVehiculoRequestDto;
import com.gearborn.motors.gearbornMotors.domain.services.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gasto")
public class GastoController {
    private final GastoService gastoService;

    @Autowired
    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    @PostMapping("/registrarGasto")
    public ResponseEntity<?> saveGasto(@RequestBody CompraGastoRequestDto compraGastoRequestDto) {
        try {
            gastoService.save(compraGastoRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/getGastos")
    public List<GastoFrontDto> getComercialesVenta() {
        return gastoService.getAll();
    }

    @GetMapping("/getGastosByMatricula/{matricula}")
    public List<GastoDto> getGastosByMatricula(@PathVariable String matricula) {
        return gastoService.getAllByMatricula(matricula);
    }

    @PostMapping("/registrarGastoMatricula")
    public ResponseEntity<?> saveGastoByMatricula(@RequestBody GastoVehiculoRequestDto gasto){
        try {
            gastoService.saveByMatricula(gasto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
