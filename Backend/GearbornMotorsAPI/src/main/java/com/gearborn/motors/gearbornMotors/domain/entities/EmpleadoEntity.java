package com.gearborn.motors.gearbornMotors.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDate;
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

    @Column(nullable = false)
    @ColumnTransformer(write = "MD5(?)")//Enciptamos la contraseña
    private String contrasena;

    @Column(name = "tipo_empleado", nullable = false, length = 25)
    private String tipo;

    @Column(nullable = false )
    private boolean suspendido = false;

    @Column(name = "dni", nullable = false, length = 9, unique = true)
    private String dni;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 75)
    private String nombre;

    @Column(nullable = false, length = 75)
    private String apellidos;

    @Column(name = "fecha_nacimiento", columnDefinition = "DATETIME", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_contrato", columnDefinition = "DATETIME", nullable = false)
    private LocalDate fechaContrato;

    @Column(name = "numero_telefono", nullable = false, length = 9)
    private int numeroTelefono;

    //FK

    //Un empleado -> muchas ventas (OneToMany)
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<VentaEntity> ventas;

    //Un empleado -> muchos gastos (OneToMany)
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<GastoEntity> gastos;
}
