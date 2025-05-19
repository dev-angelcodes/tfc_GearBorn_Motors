package com.gearbornmotors.front.gearbornmotorsfront.Controller;


import com.gearbornmotors.front.gearbornmotorsfront.Dto.Gastos.GastoDto;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GastosConcesionario {

    @FXML public VBox containerGastos;

    @FXML
    public void initialize() {
        cargarGastos();
    }

    private void cargarGastos() {
        List<GastoDto> gastos = obtenerGastos();
        for (GastoDto gasto : gastos) {
            HBox gastoHBox = crearHBoxGasto(gasto);
            containerGastos.getChildren().add(gastoHBox);
        }
    }

    private HBox crearHBoxGasto(GastoDto gasto) {
        HBox hbox = new HBox(15);
        hbox.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-border-radius: 6; -fx-background-radius: 6;");
        hbox.setAlignment(Pos.CENTER_LEFT);

        Label fechaLabel = new Label("Fecha: " + gasto.getFecha());
        Label importeLabel = new Label("Importe: " + gasto.getImporte() + " €");
        Label proveedorLabel = new Label("Proveedor: " + gasto.getNombreProv());
        Label descripcionLabel = new Label(gasto.getDescripcion());

        VBox datos = new VBox(5, fechaLabel, importeLabel, proveedorLabel, descripcionLabel);
        hbox.getChildren().add(datos);
        return hbox;
    }

    private List<GastoDto> obtenerGastos() {
        List<GastoDto> lista = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/gearBorn/api/gasto/getGastos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String json = reader.lines().reduce("", String::concat);

                Gson gson = createGsonConLocalDate();
                Type tipoLista = new TypeToken<List<GastoDto>>() {}.getType();
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
