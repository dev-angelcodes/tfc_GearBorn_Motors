package com.gearborn.motors.gearbornMotors.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @Column(name = "id_cliente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dni_nif", nullable = false, length = 9, unique = true)
    private String dni;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 75)
    private String nombre;

    @Column(nullable = false, length = 75)
    private String apellidos;

    @Column(name = "numero_telefono", nullable = false, length = 9)
    private int numTelefono;

    @Column(name = "fecha_nacimiento", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechaNacimiento;


    private String direccion;
}
