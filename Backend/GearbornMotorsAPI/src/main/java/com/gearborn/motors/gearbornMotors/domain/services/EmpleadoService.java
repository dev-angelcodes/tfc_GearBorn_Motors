package com.gearborn.motors.gearbornMotors.domain.services;

import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.EmpleadoDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.IdEmpleadoDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.RegisterRequestEmployeDto;
import com.gearborn.motors.gearbornMotors.application.mappers.EmpleadoMappper;
import com.gearborn.motors.gearbornMotors.domain.entities.EmpleadoEntity;
import com.gearborn.motors.gearbornMotors.infrastructure.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public Optional<EmpleadoDto> getEmpleadoById(int idEmpleado) {
        Optional<EmpleadoEntity> empleado = empleadoRepository.findById(idEmpleado);

        return Optional.ofNullable(EmpleadoMappper.entityToDto(empleado));
    }

    //Obtener comerciales de venta disponibles
    public List<EmpleadoDto> getAll(){
        List<EmpleadoEntity> empleadosEntity = empleadoRepository.findAll();

        List<EmpleadoDto> empleadosDto = new ArrayList<>();

        for(EmpleadoEntity empleado : empleadosEntity){
            if(empleado.getTipo().equals("Comercial de ventas") && !empleado.isSuspendido()){
                EmpleadoDto dto = EmpleadoMappper.entityToDto(Optional.of(empleado));
                empleadosDto.add(dto);
            }
        }
        return empleadosDto;
    }

    public Optional<IdEmpleadoDto> idEmpleadoDto(String email) {
        Optional<EmpleadoEntity> empleado = empleadoRepository.findByEmail(email);

        if (empleado.isPresent()) {
            IdEmpleadoDto dto = EmpleadoMappper.toIdEmpleadoDto(empleado.get());
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    public void suspenderEmpelado(String email) {
        Optional<EmpleadoEntity> empleado = empleadoRepository.findByEmail(email);

        if (empleado.isPresent()) {
            EmpleadoEntity empleadoEntity = empleado.get();
            empleadoEntity.setSuspendido(true);
            empleadoRepository.save(empleadoEntity);
        }
    }
}