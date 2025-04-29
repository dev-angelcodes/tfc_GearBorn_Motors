package com.gearborn.motors.gearbornMotors.application.dtos.Vehiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompraVehiculoRequestDto {
    private String matricula;
    private String tipo;
    private String marca;
    private String modelo;
    private int anoFabricacion;
    private double km;
    private String estado;
    private String tipoCombustible;
    private String tipoCambio;
    private String color;
    private String img;
}
