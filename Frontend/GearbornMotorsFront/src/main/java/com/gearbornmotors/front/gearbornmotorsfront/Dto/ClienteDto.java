package com.gearbornmotors.front.gearbornmotorsfront.Dto;

import java.time.LocalDate;

public class ClienteDto {
    private Integer id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String contrasena;
    private String direccion;
    private int numTelefono;
    private LocalDate fechaNacimiento;


    //Constructor

    public ClienteDto(String nombre, String apellidos, String dni, String email, String contrasena, String direccion, int numTelefono, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.numTelefono = numTelefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    //Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(int numTelefono) {
        this.numTelefono = numTelefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getId() {return id;}
}
