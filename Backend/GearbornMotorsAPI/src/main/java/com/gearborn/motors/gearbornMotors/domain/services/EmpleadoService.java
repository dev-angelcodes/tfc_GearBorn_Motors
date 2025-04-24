package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.domain.entities.EmpleadoEntity;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }


    // Guardar un empleado en la base de datos
    public void save(EmpleadoEntity empleado) {
        if(empleado.getId() != null && empleadoRepository.findById(empleado.getId()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario registrado con ese email");
        }else{
            empleadoRepository.save(empleado);
        }
    }
}
