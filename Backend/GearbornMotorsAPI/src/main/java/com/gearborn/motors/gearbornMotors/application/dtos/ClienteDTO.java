package com.gearborn.motors.gearbornMotors.application.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
}
