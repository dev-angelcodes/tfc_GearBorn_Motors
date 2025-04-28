package com.gearborn.motors.gearbornMotors.application.mappers;

import com.gearborn.motors.gearbornMotors.application.dtos.Empleado.RegisterRequestEmployeDto;
import com.gearborn.motors.gearbornMotors.domain.entities.EmpleadoEntity;

import java.time.LocalDate;

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
}
