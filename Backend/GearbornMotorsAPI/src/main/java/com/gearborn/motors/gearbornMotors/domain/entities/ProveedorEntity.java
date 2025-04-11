package com.gearborn.motors.gearbornMotors.domain.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorEntity {
    @Id
    @Column(name = "id_proveedor", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 75)
    private String nombre;

    @Column(name = "dni_nif", nullable = false, length = 9, unique = true)
    private String dni;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "numero_telefono", nullable = false, length = 9)
    private int numTelefono;
}
