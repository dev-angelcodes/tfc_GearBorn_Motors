package com.gearbornmotors.front.gearbornmotorsfront.Dto.Gastos;

import java.time.LocalDate;

public class GastoVehiculoDto {
    private Double importe;
    private String nombreProv;

    private int idEmpleado;
    private String matricula;

    private LocalDate fecha = LocalDate.now();
    private boolean compra = false;

    private String descripcion;

    @Override
    public String toString() {
        return "GastoVehiculoDto{" +
                "importe=" + importe +
                ", nombreProv='" + nombreProv + '\'' +
                ", idEmpleado=" + idEmpleado +
                ", matricula='" + matricula + '\'' +
                ", fecha=" + fecha +
                ", compra=" + compra +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isCompra() {
        return compra;
    }

    public void setCompra(boolean compra) {
        this.compra = compra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
