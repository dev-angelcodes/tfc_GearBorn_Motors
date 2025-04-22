package com.gearbornmotors.front.gearbornmotorsfront.Dto;

public class ClientRegisterRequestDto {
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String contrasena;
    private String direccion;
    private int numTelefono;
    private String fechaNacimiento;

    public ClientRegisterRequestDto(String nombre, String apellidos, String dni, String email, String contrasena, String direccion, int numTelefono, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.numTelefono = numTelefono;
        this.fechaNacimiento = fechaNacimiento;
    }
}
