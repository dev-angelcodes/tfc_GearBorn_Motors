package com.gearborn.motors.gearbornMotors.application.dtos.Venta;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaRequestDto {
    private Double importe;
    private LocalDateTime fecha;

    private Integer idCliente;
    private Integer idEmpleado;
    private Integer idVehiculo;
}

