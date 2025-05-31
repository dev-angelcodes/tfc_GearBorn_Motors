package com.gearborn.motors.gearbornMotors.application.dtos.Venta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto {
    private Double importe;
    private LocalDateTime fecha;
    private LocalDateTime garantia;

    private String empleado;
    private String cliente;
    private String descripcionVehiculo;
}