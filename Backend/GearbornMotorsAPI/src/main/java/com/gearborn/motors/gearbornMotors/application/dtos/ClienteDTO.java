package com.gearborn.motors.gearbornMotors.application.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {

    private String email;
    private String nombre;

}
