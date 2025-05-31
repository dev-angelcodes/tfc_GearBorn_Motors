package com.gearborn.motors.gearbornMotors.application.dtos.Gastos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GastoFrontDto {
    private Double importe;
    private LocalDate fecha;
    private String nombreProv;
    private String descripcion;
    private String vehiculo;
}
