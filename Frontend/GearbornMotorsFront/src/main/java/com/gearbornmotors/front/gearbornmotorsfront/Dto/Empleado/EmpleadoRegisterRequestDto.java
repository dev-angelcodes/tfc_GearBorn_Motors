package com.gearbornmotors.front.gearbornmotorsfront.Dto.Empleado;

public class EmpleadoRegisterRequestDto {
    String contrasena;
    String tipo;
    boolean suspendido;
    String dni;
    String email;
    String nombre;
    String apellidos;
    String fechaNacimiento;
    String fechaContrato;
    int numeroTelefono;

    public EmpleadoRegisterRequestDto() {
    }

    public EmpleadoRegisterRequestDto(String contrasena, String tipo, boolean suspendido, String dni, String email, String nombre, String apellidos, String fechaNacimiento, String fechaContrato, int numeroTelefono) {
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.suspendido = suspendido;
        this.dni = dni;
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContrato = fechaContrato;
        this.numeroTelefono = numeroTelefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isSuspendido() {
        return suspendido;
    }

    public void setSuspendido(boolean suspendido) {
        this.suspendido = suspendido;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(String fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}
