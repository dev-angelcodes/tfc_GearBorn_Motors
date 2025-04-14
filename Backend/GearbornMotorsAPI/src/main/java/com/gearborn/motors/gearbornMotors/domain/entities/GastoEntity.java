package com.gearborn.motors.gearbornMotors.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gastos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GastoEntity {

    @Id
    @Column(name = "id_gasto", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "Decimal(9,2)", nullable = false)
    private Double importe;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fecha;

    @Column(name = "compra_vehiculo", nullable = false)
    private boolean venta;

    @Column(length = 250)
    private String descripcion;

    //FK

    //Muchos gastos -> un empleado (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor ", nullable = false)
    private ProveedorEntity proveedor;

    //Muchos gastos -> Un empleado (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado", nullable = false)
    private EmpleadoEntity empleado;

    //Muchos gastos -> Un vehiculo (ManyToOne) -> El vehiculo puede revenderse
    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo", nullable = false)
    private VehiculoEntity vehiculo;
}
