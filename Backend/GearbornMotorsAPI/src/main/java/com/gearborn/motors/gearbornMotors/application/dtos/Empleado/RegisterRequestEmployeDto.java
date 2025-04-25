package com.gearborn.motors.gearbornMotors.application.dtos.Empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestEmployeDto {
    String contrasena;
    String tipo;
    boolean suspendido;
    String dni;
    String email;
    String nombre;
    String apellidos;
    String fechaNacimiento;
    String fechaContrato;
    int numeroTelefono;


}
