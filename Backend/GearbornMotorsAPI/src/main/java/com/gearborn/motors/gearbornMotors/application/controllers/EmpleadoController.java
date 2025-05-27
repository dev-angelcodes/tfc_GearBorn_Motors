package com.gearborn.motors.gearbornMotors.application.controllers;

import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.EmpleadoDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.IdEmpleadoDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.REmployeDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.RegisterRequestEmployeDto;
import com.gearborn.motors.gearbornMotors.domain.entities.EmpleadoEntity;
import com.gearborn.motors.gearbornMotors.domain.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/registrarEmpleado")
    public ResponseEntity<?> saveEmpleado(@RequestBody RegisterRequestEmployeDto empleado) {
        try {
            empleadoService.save(empleado);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody REmployeDto empleadoDto) {

        Optional<EmpleadoEntity> empleado = empleadoService.login(empleadoDto.getId(), empleadoDto.getContrasenaHasheada());

        if(empleado.isPresent()){
            return ResponseEntity.ok("LoginExistoso");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @GetMapping("/getEmpleadoById/{idEmpleado}")
    public Optional<EmpleadoDto> getEmpleadoById(@PathVariable int idEmpleado){
        return empleadoService.getEmpleadoById(idEmpleado);
    }

    @GetMapping("/getComercialesVenta")
    public List<EmpleadoDto> getComercialesVenta() {
        return empleadoService.getAll();
    }

    @PostMapping("/idEmpleado")
    public ResponseEntity<?> idEmpleadoDto(@RequestBody String email) {
        Optional<IdEmpleadoDto> empleado = empleadoService.idEmpleadoDto(email);

        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Empleado no encontrado con email: " + email);
        }
    }
}