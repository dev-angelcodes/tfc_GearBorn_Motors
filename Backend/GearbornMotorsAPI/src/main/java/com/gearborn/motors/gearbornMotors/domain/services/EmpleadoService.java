package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.RegisterRequestEmployeDto;
import com.gearborn.motors.gearbornMotors.application.mappers.EmpleadoMappper;
import com.gearborn.motors.gearbornMotors.domain.entities.EmpleadoEntity;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }


    // Guardar un empleado en la base de datos
    public void save(RegisterRequestEmployeDto registerRequestEmployeDto) {
        EmpleadoEntity empleado = EmpleadoMappper.toEntity(registerRequestEmployeDto);
        if(empleado.getId() != null && empleadoRepository.findById(empleado.getId()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario registrado con ese email");
        }else{
            empleadoRepository.save(empleado);
        }
    }

    //Obtener un Empleado por ID y verificar contraseña
    public Optional<EmpleadoEntity> login(int id, String contrasenaHasheada) {

        Optional<EmpleadoEntity> empleado = empleadoRepository.findById(id);

        if(empleado.isPresent() && empleado.get().getContrasena().equals(contrasenaHasheada)){
            return empleado;
        }else{
            return Optional.empty();
        }
    }
}
