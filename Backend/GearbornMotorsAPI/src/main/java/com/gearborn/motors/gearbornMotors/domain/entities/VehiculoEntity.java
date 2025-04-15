package com.gearborn.motors.gearbornMotors.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "vehiculos")
@Getter
@Setter
@NoArgsConstructor
public class VehiculoEntity {
    @Id
    @Column(name = "id_vehiculo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 7)
    private String matricula;

    @Column(nullable = false, length = 7)
    private String tipo;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false, length = 4)
    private int anio;

    @Column(name = "Kilometros", nullable = false)
    private Double km;

    @Column(length = 25, nullable = false) //Disponible o vendido o reservado
    private String estado;

    @Column(name = "tipo_combustible", nullable = false, length = 25)
    private String tipoCombustible;

    @Column(name = "tipo_cambio", nullable = false, length = 25)
    private String tipoCambio;

    @Column(nullable = false, length = 7)
    private String color;

    //FK
    //Un vehiculo -> muchas ventas (OneToMany)
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<VentaEntity> ventas;

    //Un vehiculo -> muchos gastos (OneToMany)
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<GastoEntity> gastos;


    //CONSTRUCTOR SIN ID

    public VehiculoEntity(String matricula, String tipo, String marca, String modelo, int anio, Double km, String estado, String tipoCombustible, String tipoCambio, String color) {
        this.matricula = matricula;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.km = km;
        this.estado = estado;
        this.tipoCombustible = tipoCombustible;
        this.tipoCambio = tipoCambio;
        this.color = color;
    }
}
