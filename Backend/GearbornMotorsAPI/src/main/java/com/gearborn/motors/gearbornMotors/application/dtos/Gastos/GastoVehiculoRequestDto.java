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
public class GastoVehiculoRequestDto {
    private Double importe;
    private String nombreProv;

    private int idEmpleado;
    private String matricula;

    private LocalDate fecha;
    private boolean compra = false;

    private String descripcion;
}
