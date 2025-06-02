package com.gearborn.motors.gearbornMotors.application.controllers;

import com.gearborn.motors.gearbornMotors.application.dtos.Vehiculo.CompraVehiculoRequestDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Vehiculo.IdVehiculoDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Vehiculo.VehiculoDto;
import com.gearborn.motors.gearbornMotors.domain.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {
    private final VehiculoService vehiculoService;

    @Autowired
    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PostMapping("/registrarVehiculo")
    public ResponseEntity<?> saveVehiculo(@RequestBody CompraVehiculoRequestDto compraVehiculoRequestDto) {
        try{
            vehiculoService.save(compraVehiculoRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getIdByMatricula/{matricula}")
    public Optional<Integer> getIdByMatricula(@PathVariable String matricula) {
        return vehiculoService.getIdByMatricula(matricula);
    }

    @GetMapping("/getAllVehiculos")
    public ResponseEntity<List<VehiculoDto>> getAllVehiculos() {
        List<VehiculoDto> vehiculos = vehiculoService.getAll();
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/AllVehiculos")
    public ResponseEntity<List<VehiculoDto>> getAll() {
        List<VehiculoDto> vehiculos = vehiculoService.getAllVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/getMarcas")
    public ResponseEntity<List<String>> getMarcas() {
        List<String> marcas = vehiculoService.getMarcasDisponibles();
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/getModelos/{marca}")
    public ResponseEntity<List<String>> getModelos(@PathVariable String marca) {
        List<String> modelos = vehiculoService.getModelosDisponibles(marca);
        return ResponseEntity.ok(modelos);
    }

    @GetMapping("/getVehiculosFiltrados")
    public ResponseEntity<List<VehiculoDto>> getVehiculosFiltrados(@RequestParam String marca,
                                                                   @RequestParam(required = false) String modelo) {
        List<VehiculoDto> vehiculos = vehiculoService.getVehiculosFiltrados(marca, modelo);
        return ResponseEntity.ok(vehiculos);
    }
}
