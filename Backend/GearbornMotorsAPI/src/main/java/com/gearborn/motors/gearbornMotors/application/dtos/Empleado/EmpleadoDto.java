package com.gearborn.motors.gearbornMotors.application.dtos.Empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDto {
    private int id;
    private String email;
    private String nombre;
}
