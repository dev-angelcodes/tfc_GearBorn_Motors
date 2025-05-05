package com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente;

public class ClienteDto {
    private int id;
    public String email;
    public String nombre;
    public String direccion;
    public String telefono;
    public String contrasena;
    public String dni;
    public String fechaNacimiento;
    public String apellidos;
    public int numTelefono;

    public ClienteDto() {
    }

    public ClienteDto(int id, String email, String nombre, String direccion, String telefono) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "ClienteDto{" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
