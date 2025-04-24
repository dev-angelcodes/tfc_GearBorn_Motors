package com.gearborn.motors.gearbornMotors.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "contrasenha",nullable = false)
    @ColumnTransformer(write = "MD5(?)")//Enciptamos la contraseña
    private String contrasena;

    @Column(name = "dni_nif", nullable = false, length = 9)
    private String dni;

    @Column(nullable = false, length = 75)
    private String nombre;

    @Column(length = 75)
    private String apellidos;

    @Column(name = "numero_telefono", nullable = false, length = 9)
    private int numTelefono;

    private String direccion;

    //FK

    //Un cliente -> muchas ventas (OneToMany)
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<VentaEntity> ventas;
}
