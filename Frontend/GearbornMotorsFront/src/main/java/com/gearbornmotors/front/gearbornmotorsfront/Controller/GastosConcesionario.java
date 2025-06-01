package com.gearbornmotors.front.gearbornmotorsfront.Controller;


import com.gearbornmotors.front.gearbornmotorsfront.Dto.Gastos.GastoCompraDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Venta.VentaDto;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GastosConcesionario {

    @FXML public VBox containerGastos;
    @FXML public VBox containerVentas;

    @FXML
    public void initialize() {
        cargarGastos();
        cargarVentas();
    }

    private void cargarVentas() {
        List<VentaDto> ventas = obtenerVentas();
        for (VentaDto venta : ventas) {
            HBox ventaHBox = crearHBoxVenta(venta);
            containerVentas.getChildren().add(ventaHBox);
        }
    }

    private HBox crearHBoxVenta(VentaDto venta) {
        HBox hbox = new HBox(15);
        hbox.setStyle("-fx-padding: 12; -fx-background-color: #e0f7fa; -fx-border-color: #26a69a; -fx-border-radius: 6; -fx-background-radius: 6;");
        hbox.setAlignment(Pos.TOP_LEFT);

        Label fechaLabel = new Label("📅 Fecha: " + venta.getFecha().toLocalDate());
        Label importeLabel = new Label("💰 Importe: " + venta.getImporte() + " €");
        HBox linea1 = new HBox(20, fechaLabel, importeLabel);

        Label clienteLabel = new Label("👤 Cliente: " + venta.getCliente());
        Label empleadoLabel = new Label("🧑‍💼 Empleado: " + venta.getEmpleado());

        Label descripcionVehiculo = new Label("🚗 Vehículo: " + venta.getDescripcionVehiculo());
        descripcionVehiculo.setWrapText(true);

        VBox datos = new VBox(5, linea1, clienteLabel, empleadoLabel, descripcionVehiculo);
        hbox.getChildren().add(datos);
        return hbox;
    }



    private List<VentaDto> obtenerVentas() {
        List<VentaDto> lista = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/gearBorn/api/venta/getVentas");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String json = reader.lines().reduce("", String::concat);

                Gson gson = createGsonConFechas();
                Type tipoLista = new TypeToken<List<VentaDto>>() {}.getType();
                lista = gson.fromJson(json, tipoLista);

                reader.close();
            } else {
                System.out.println("Error al obtener ventas: " + conn.getResponseCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Gson createGsonConFechas() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>)
                        (json, type, context) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>)
                        (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                        (json, type, context) -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                        (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
                .create();
    }

    private void cargarGastos() {
        List<GastoCompraDto> gastos = obtenerGastos();
        for (GastoCompraDto gasto : gastos) {
            HBox gastoHBox = crearHBoxGasto(gasto);
            containerGastos.getChildren().add(gastoHBox);
        }
    }

    private HBox crearHBoxGasto(GastoCompraDto gasto) {
        HBox hbox = new HBox(15);
        hbox.setStyle("-fx-padding: 12; -fx-background-color: #f4f4f4; -fx-border-color: #ccc; -fx-border-radius: 6; -fx-background-radius: 6;");
        hbox.setAlignment(Pos.TOP_LEFT);

        Label fechaLabel = new Label("📅 Fecha: " + gasto.getFecha());
        Label importeLabel = new Label("💰 Importe: " + gasto.getImporte() + " €");
        HBox linea1 = new HBox(20, fechaLabel, importeLabel);

        Label proveedorLabel = new Label("🏢 Proveedor: " + gasto.getNombreProv());
        Label vehiculoLabel = new Label("🚗 Vehículo: " + gasto.getVehiculo());
        HBox linea2 = new HBox(20, proveedorLabel, vehiculoLabel);

        VBox datos = new VBox(5);

        if (gasto.isCompra()) {
            Label descripcionLabel = new Label("🛒 Este gasto se corresponde con la compra del vehículo");
            descripcionLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            datos.getChildren().add(descripcionLabel);
        }

        datos.getChildren().addAll(linea1, linea2);
        hbox.getChildren().add(datos);
        return hbox;
    }


    private List<GastoCompraDto> obtenerGastos() {
        List<GastoCompraDto> lista = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/gearBorn/api/gasto/getGastos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String json = reader.lines().reduce("", String::concat);

                Gson gson = createGsonConFechas(); // este ya lo tienes
                Type tipoLista = new TypeToken<List<GastoCompraDto>>() {}.getType();
                lista = gson.fromJson(json, tipoLista);

                reader.close();
            } else {
                System.out.println("Error al obtener gastos: " + conn.getResponseCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }


    // 👉 Adaptador para LocalDate
    private Gson createGsonConLocalDate() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>)
                        (json, typeOfT, context) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                .create();
    }
}
