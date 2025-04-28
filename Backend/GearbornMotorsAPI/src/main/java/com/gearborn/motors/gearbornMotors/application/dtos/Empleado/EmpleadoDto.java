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
    private String contrasena;
    private String tipo;
    private boolean suspendido;
    private String dni;
    private String email;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String fechaContrato;
    private int numeroTelefono;
}
