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
public class CompraGastoRequestDto {
    private Double importe;
    private String nombreProv;

    private int idEmpleado;
    private int idVehiculo;


    public String getDescripcion() {
        return "Precio de compra del vehiculo: " + this.importe + "€ \nVendedor: " + this.nombreProv
                + "\nFecha de compra: " + LocalDate.now() + "\nVehiculo: " + this.idVehiculo
                + "\nEmpleado: " + this.idEmpleado;
    }
}