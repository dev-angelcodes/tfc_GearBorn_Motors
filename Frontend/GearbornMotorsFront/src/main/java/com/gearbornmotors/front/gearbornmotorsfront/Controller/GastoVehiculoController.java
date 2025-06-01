package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.Empleado.EmpleadoDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Gastos.GastoVehiculoDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Vehiculo.VehiculoDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GastoVehiculoController extends PanelControlController {

    @FXML
    private VBox vehiculosContainer;

    @FXML
    public void initialize() throws IOException {
        cargarVehiculos();
    }

    private void cargarVehiculos() {
        List<VehiculoDto> vehiculos = obtenerVehiculos();
        for (VehiculoDto v : vehiculos) {
            HBox hbox = crearHBox(v);
            vehiculosContainer.getChildren().add(hbox);
        }
    }

    private List<VehiculoDto> obtenerVehiculos() {
        List<VehiculoDto> vehiculos = new ArrayList<>();
        try {
            String url = "http://localhost:8080/gearBorn/api/vehiculo/getAllVehiculos";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String json = response.body();

                Gson gson = new Gson();
                VehiculoDto[] arrayVehiculos = gson.fromJson(json, VehiculoDto[].class);
                vehiculos = List.of(arrayVehiculos);
            } else {
                System.out.println("Error al obtener vehículos: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    private HBox crearHBox(VehiculoDto v) {
        HBox hbox = configurarHBoxContenedor();

        ImageView imagen = crearImagenVehiculo(v);
        VBox datosVehiculo = crearVBoxDatosVehiculo(v);
        VBox acciones = crearVBoxAcciones(v);

        hbox.getChildren().addAll(imagen, datosVehiculo, acciones);
        HBox.setHgrow(datosVehiculo, Priority.ALWAYS);

        return hbox;
    }

    // 📦 Contenedor principal del vehículo
    private HBox configurarHBoxContenedor() {
        HBox hbox = new HBox(20);
        hbox.setStyle("-fx-padding: 15; -fx-background-color: #f2f2f2; -fx-border-color: #ccc; " +
                "-fx-border-radius: 10; -fx-background-radius: 10;");
        hbox.setPrefHeight(150);
        hbox.setAlignment(Pos.CENTER_LEFT);
        return hbox;
    }

    // 🧾 Datos del vehículo (marca, modelo, año, km)
    private VBox crearVBoxDatosVehiculo(VehiculoDto v) {
        VBox datos = new VBox(8);
        datos.setAlignment(Pos.CENTER_LEFT);
        datos.setPadding(new Insets(5));

        Label marcaModelo = new Label(v.getMarca() + " " + v.getModelo());
        marcaModelo.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

        Label km = new Label("Kilómetros: " + String.format("%,.1f", v.getKm()));
        Label anio = new Label("Año: " + v.getAnio());

        datos.getChildren().addAll(marcaModelo, km, anio);
        return datos;
    }

    // 🧩 Acciones (botón y formulario)
    private VBox crearVBoxAcciones(VehiculoDto v) {
        VBox accionesBox = new VBox(10);
        accionesBox.setAlignment(Pos.CENTER_RIGHT);
        accionesBox.setPadding(new Insets(5));

        Button btnAñadirGasto = new Button("Añadir gasto");
        btnAñadirGasto.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox formulario = crearFormularioGasto(v, btnAñadirGasto);
        formulario.setVisible(false);

        btnAñadirGasto.setOnAction(e -> {
            btnAñadirGasto.setVisible(false);
            formulario.setVisible(true);
        });

        accionesBox.getChildren().addAll(btnAñadirGasto, formulario);
        return accionesBox;
    }

    private VBox crearFormularioGasto(VehiculoDto v, Button botonMostrar) {
        VBox formulario = new VBox(8); // Espaciado un poco mayor
        formulario.setPadding(new Insets(10));
        formulario.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-radius: 6;");

        //Campo: Importe
        Label labelImporte = new Label("💶 Importe del gasto:");
        TextField importeField = new TextField();
        importeField.setPromptText("Ej: 1500.00");

        //Campo: Descripción
        Label labelDescripcion = new Label("📝 Descripción del gasto:");
        TextField descripcionField = new TextField();
        descripcionField.setPromptText("Ej: Reparación de frenos");

        //Campo: Proveedor
        Label labelProveedor = new Label("🏢 Nombre del proveedor:");
        TextField proveedorField = new TextField();
        proveedorField.setPromptText("Ej: Taller Rápido S.L.");

        //Botón Guardar
        Button guardarBtn = new Button("Guardar");
        guardarBtn.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");

        guardarBtn.setOnAction(e -> {
            try {
                double importe = Double.parseDouble(importeField.getText());
                String descripcion = descripcionField.getText();
                String proveedor = proveedorField.getText();

                GastoVehiculoDto gasto = new GastoVehiculoDto();
                gasto.setImporte(importe);
                gasto.setDescripcion(descripcion);
                gasto.setNombreProv(proveedor);
                gasto.setMatricula(v.getMatricula());
                gasto.setIdEmpleado(datosEmpleadoLogueado().getId());
                gasto.setFecha(LocalDate.now());
                gasto.setCompra(false);

                System.out.println(gasto);
                enviarGastoAlBackend(gasto);

                formulario.setVisible(false);
                botonMostrar.setVisible(true);

            } catch (NumberFormatException ex) {
                System.out.println("⚠ El importe debe ser un número válido.");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        formulario.getChildren().addAll(
                labelImporte, importeField,
                labelDescripcion, descripcionField,
                labelProveedor, proveedorField,
                guardarBtn
        );

        return formulario;
    }

    private void enviarGastoAlBackend(GastoVehiculoDto gasto) throws IOException {
        String url = "http://localhost:8080/gearBorn/api/gasto/registrarGastoMatricula";

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>)
                        (fecha, tipo, contexto) -> new com.google.gson.JsonPrimitive(fecha.toString()))
                .create();

        String json = gson.toJson(gasto);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() == 201) {
                        Platform.runLater(() -> System.out.println("✅ Gasto enviado correctamente."));
                    } else {
                        Platform.runLater(() -> System.out.println("Error al enviar gasto: " + response.body()));
                    }
                })
                .exceptionally(e -> {
                    Platform.runLater(() -> System.out.println("Error en la petición: " + e.getMessage()));
                    return null;
                });
    }



    private ImageView crearImagenVehiculo(VehiculoDto v) {
        ImageView img = new ImageView();
        try {
            String ruta = "/com/gearbornmotors/front/gearbornmotorsfront/img/" + v.getImg();
            Image imagen = new Image(getClass().getResourceAsStream(ruta));
            img.setImage(imagen);
        } catch (Exception e) {
            String rutaDefault = "/com/gearbornmotors/front/gearbornmotorsfront/img/imgDefaultVehiculo.png";
            Image imagen = new Image(getClass().getResourceAsStream(rutaDefault));
            img.setImage(imagen);
        }
        img.setFitHeight(120);
        img.setPreserveRatio(true);
        return img;
    }
}
