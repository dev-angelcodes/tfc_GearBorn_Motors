package com.gearborn.motors.gearbornMotors.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaEntity {

    @Id
    @Column(name = "id_venta", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "Decimal(9,2)", nullable = false)
    private Double importe;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fecha;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime garantia = LocalDateTime.now().plusYears(2); //2 año de garantia, por defecto

    //FK

    //Muchas ventas -> un cliente (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    //Muchas ventas -> Un empleado (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado", nullable = false)
    private EmpleadoEntity empleado;

    //Muchas ventas -> Un vehiculo (ManyToOne) -> El vehiculo puede revenderse
    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo", nullable = false)
    private VehiculoEntity vehiculo;
}