package com.gearbornmotors.front.gearbornmotorsfront.Dto.Venta;

import java.time.LocalDateTime;

public class VentaDto {
    private Double importe;
    private LocalDateTime fecha;
    private LocalDateTime garantia;

    private String empleado;
    private String cliente;
    private String descripcionVehiculo;

    @Override
    public String toString() {
        return "VentaDto{" +
                "importe=" + importe +
                ", fecha=" + fecha +
                ", garantia=" + garantia +
                ", Empleado='" + empleado + '\'' +
                ", Cliente='" + cliente + '\'' +
                ", descripcionVehiculo='" + descripcionVehiculo + '\'' +
                '}';
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getGarantia() {
        return garantia;
    }

    public void setGarantia(LocalDateTime garantia) {
        this.garantia = garantia;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescripcionVehiculo() {
        return descripcionVehiculo;
    }

    public void setDescripcionVehiculo(String descripcionVehiculo) {
        this.descripcionVehiculo = descripcionVehiculo;
    }
}
