package com.gearbornmotors.front.gearbornmotorsfront.Dto;

import java.time.LocalDate;

public class ClienteDto {
    public String email;
    public String dni;
    public String nombre;
    public String apellidos;
    public int numTelefono;
    public String direccion;
    public String fechaNacimiento;


    //Constructor

    public ClienteDto(String nombre, String apellidos, String dni, String email, String contrasena, String direccion, int numTelefono, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "ClienteDto{" +
                "email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", numTelefono=" + numTelefono +
                ", direccion='" + direccion + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                '}';
    }
}
