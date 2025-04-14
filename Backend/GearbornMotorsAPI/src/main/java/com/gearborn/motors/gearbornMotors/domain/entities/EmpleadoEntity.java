package com.gearborn.motors.gearbornMotors.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "empleados")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class EmpleadoEntity {

    @Id
    @Column(name = "id_empleado", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contrasena",nullable = false)
    private String contrasena;

    @Column(name = "dni", nullable = false, length = 9, unique = true)
    private String dni;

    @Column(nullable = false )
    private boolean suspendido = false;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String puesto;

    @Column(nullable = false, length = 75)
    private String nombre;

    @Column(nullable = false, length = 75)
    private String apellidos;

    @Column(name = "fecha_nacimiento", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechaNacimiento;

    @Column(name = "fecha_contrato", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechaContrato;

    @Column(name = "numero_telefono", nullable = false, length = 9)
    private int numeroTelefono;

    //FK

    //Un empleado -> muchos gastos (OneToMany)
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<VentaEntity> ventas;

    //Un empleado -> muchos gastos (OneToMany)
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<GastoEntity> gastos;
}
