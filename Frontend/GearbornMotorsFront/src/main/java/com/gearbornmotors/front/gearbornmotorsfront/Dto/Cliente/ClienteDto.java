package com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente;

public class ClienteDto {
    private int id;
    public String email;
    public String nombre;

    public ClienteDto(int id, String email, String nombre) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "ClienteDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
