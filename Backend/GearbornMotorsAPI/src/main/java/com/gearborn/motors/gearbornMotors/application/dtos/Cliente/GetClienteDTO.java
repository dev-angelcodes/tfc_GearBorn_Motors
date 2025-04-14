package com.gearborn.motors.gearbornMotors.application.dtos.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetClienteDTO {
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String contrasena;
    private String direccion;
    private int numTelefono;
    private LocalDate fechaNacimiento;
}
