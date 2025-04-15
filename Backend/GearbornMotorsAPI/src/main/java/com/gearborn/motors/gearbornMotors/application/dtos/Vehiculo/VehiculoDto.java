package com.gearborn.motors.gearbornMotors.application.dtos.Vehiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDto {
    private String matricula;
    private String tipo;
    private String marca;
    private String modelo;
    private int anio;
    private Double km;
    private String estado;
    private String tipoCombustible;
    private String tipoCambio;
    private String color;
    private String img;
}
