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
public class ClienteDTO {
    private String email;
    private String contrasena;
    private String dni;
    private String nombre;
    private String apellidos;
    private int numTelefono;
    private String direccion;
    private LocalDate fechaNacimiento;
}
