package com.gearborn.motors.gearbornMotors.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "empleados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaEntity {

    @Id
    @Column(name = "id_vehiculo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "Decimal(9,2)", nullable = false)
    private Double importe;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fecha;

    @Column(name = "compra_vehiculo", nullable = false)
    private boolean venta;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime garantia = LocalDateTime.now().plusYears(2); //2 año de garantia, por defecto

    //FK
}
