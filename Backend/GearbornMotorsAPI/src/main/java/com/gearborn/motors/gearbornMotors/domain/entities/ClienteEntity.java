package com.gearborn.motors.gearbornMotors.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

    @Column(name = "contrasenha",nullable = false)
    private String contrasena;

    @Column(nullable = false, length = 75)
    private String nombre;

    @Column(nullable = false, length = 75)
    private String apellidos;

    @Column(name = "numero_telefono", nullable = false, length = 9)
    private int numTelefono;

    @Column(name = "fecha_nacimiento", columnDefinition = "DATETIME", nullable = false)
    private LocalDate fechaNacimiento;

    private String direccion;

    //FK

    //Un cliente -> muchas ventas (OneToMany)
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<VentaEntity> ventas;
}
