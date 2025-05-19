package com.gearbornmotors.front.gearbornmotorsfront.Dto.Gastos;

import java.time.LocalDate;

public class GastoDto {
    private Integer id;
    private Double importe;
    private LocalDate fecha;
    private boolean compra;
    private String nombreProv;
    private String descripcion;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Double getImporte() { return importe; }
    public void setImporte(Double importe) { this.importe = importe; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public boolean isCompra() { return compra; }
    public void setCompra(boolean compra) { this.compra = compra; }

    public String getNombreProv() { return nombreProv; }
    public void setNombreProv(String nombreProv) { this.nombreProv = nombreProv; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return "GastoDto{" +
                "importe=" + importe +
                ", fecha=" + fecha +
                ", proveedor='" + nombreProv + '\'' +
                '}';
    }
}
