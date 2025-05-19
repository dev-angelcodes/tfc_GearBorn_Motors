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
public class GastoDto {
    private Integer id;
    private Double importe;
    private LocalDate fecha;
    private boolean compra;
    private String nombreProv;
    private String descripcion;
}
