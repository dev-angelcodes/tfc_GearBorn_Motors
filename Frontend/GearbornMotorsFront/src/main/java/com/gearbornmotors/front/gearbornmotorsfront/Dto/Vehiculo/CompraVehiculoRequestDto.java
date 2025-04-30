package com.gearbornmotors.front.gearbornmotorsfront.Dto.Vehiculo;

public class CompraVehiculoRequestDto {
    private String matricula;
    private String tipo;
    private String marca;
    private String modelo;
    private int anoFabricacion;
    private double km;
    private String estado;
    private String tipoCombustible;
    private String tipoCambio;
    private String color;
    private String img;


    public CompraVehiculoRequestDto() {
    }

    public CompraVehiculoRequestDto(String matricula, String tipo, String marca, String modelo, int anoFabricacion, double km, String estado, String tipoCombustible, String tipoCambio, String color, String img) {
        this.matricula = matricula;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacion = anoFabricacion;
        this.km = km;
        this.estado = estado;
        this.tipoCombustible = tipoCombustible;
        this.tipoCambio = tipoCambio;
        this.color = color;
        this.img = img;
    }

    @Override
    public String toString() {
        return "CompraVehiculoRequestDto{" +
                "matricula='" + matricula + '\'' +
                ", tipo='" + tipo + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anoFabricacion=" + anoFabricacion +
                ", km=" + km +
                ", estado='" + estado + '\'' +
                ", tipoCombustible='" + tipoCombustible + '\'' +
                ", tipoCambio='" + tipoCambio + '\'' +
                ", color='" + color + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(int anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
