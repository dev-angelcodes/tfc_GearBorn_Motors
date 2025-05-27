package com.gearborn.motors.gearbornMotors.application.mappers;

import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.EmpleadoDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.IdEmpleadoDto;
import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.RegisterRequestEmployeDto;
import com.gearborn.motors.gearbornMotors.domain.entities.EmpleadoEntity;

import java.time.LocalDate;
import java.util.Optional;

public class EmpleadoMappper {

    //Mapeamos de un DTO a EmpleadoEntity
    public static EmpleadoEntity toEntity(RegisterRequestEmployeDto empleado){
        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        empleadoEntity.setContrasena(empleado.getContrasena());
        empleadoEntity.setTipo(empleado.getTipo());
        empleadoEntity.setSuspendido(empleado.isSuspendido());
        empleadoEntity.setDni(empleado.getDni());
        empleadoEntity.setEmail(empleado.getEmail());
        empleadoEntity.setNombre(empleado.getNombre());
        empleadoEntity.setApellidos(empleado.getApellidos());
        empleadoEntity.setFechaNacimiento(LocalDate.parse(empleado.getFechaNacimiento()));
        empleadoEntity.setFechaContrato(LocalDate.parse(empleado.getFechaContrato()));
        empleadoEntity.setNumeroTelefono(empleado.getNumeroTelefono());
        return empleadoEntity;
    }

    public static EmpleadoDto entityToDto(Optional<EmpleadoEntity> empleado){
        if(empleado.isPresent()){
            EmpleadoDto empleadoDto = new EmpleadoDto();
            empleadoDto.setId(empleado.get().getId());
            empleadoDto.setEmail(empleado.get().getEmail());
            empleadoDto.setNombre(empleado.get().getNombre());
            return empleadoDto;
        }else{
            return null;
        }
    }

    public static IdEmpleadoDto toIdEmpleadoDto(EmpleadoEntity empleadoEntity) {
        IdEmpleadoDto empleadoDto = new IdEmpleadoDto();
        empleadoDto.setId(empleadoEntity.getId());
        return empleadoDto;
    }
}
