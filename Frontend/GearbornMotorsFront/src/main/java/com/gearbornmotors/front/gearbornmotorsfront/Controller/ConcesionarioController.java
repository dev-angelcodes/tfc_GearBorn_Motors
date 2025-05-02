package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.Vehiculo.VehiculoDto;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ConcesionarioController {
    @FXML public Label descripcionConcesionario;
    @FXML public VBox vehiculosContainer;

    @FXML
    public void initialize() {
        textoBienvenida();
        cargarVehiculos(vehiculos());
    }

    private void cargarVehiculos(List<VehiculoDto> vehiculos) {
        List<VehiculoDto> vehiculosObtenidos = vehiculos;

        for(VehiculoDto v : vehiculosObtenidos) {
            HBox hBox = crearHBox(v);
            vehiculosContainer.getChildren().add(hBox);
        }
    }

    private HBox crearHBox(VehiculoDto v) {
        HBox hbox = new HBox(20);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-padding: 15; -fx-background-color: #f2f2f2; -fx-border-color: #ccc; " +
                "-fx-border-radius: 10; -fx-background-radius: 10;");
        hbox.setPrefHeight(150);

        ImageView imagen = crearImagenVehiculo(v);
        VBox datos = crearVBoxDatosVehiculo(v);
        Button boton = crearBotonSaberMas(v);

        datos.getChildren().add(boton);
        hbox.getChildren().addAll(imagen, datos);

        return hbox;
    }

    private ImageView crearImagenVehiculo(VehiculoDto v) {
        ImageView imagenVehiculo = new ImageView();
        try {
            String ruta = "/com/gearbornmotors/front/gearbornmotorsfront/img/" + v.getImg();
            Image imagen = new Image(getClass().getResourceAsStream(ruta));
            imagenVehiculo.setImage(imagen);
            imagenVehiculo.setFitHeight(120);
            imagenVehiculo.setPreserveRatio(true);
            imagenVehiculo.setSmooth(true);
        } catch (Exception e) {
            String ruta = "/com/gearbornmotors/front/gearbornmotorsfront/img/" + "imgDefaultVehiculo.png";
            Image imagen = new Image(getClass().getResourceAsStream(ruta));
            imagenVehiculo.setImage(imagen);
            imagenVehiculo.setFitHeight(120);
            imagenVehiculo.setPreserveRatio(true);
            imagenVehiculo.setSmooth(true);
        }
        return imagenVehiculo;
    }

    private VBox crearVBoxDatosVehiculo(VehiculoDto v) {
        VBox datosVehiculo = new VBox(10);
        datosVehiculo.setPrefHeight(150);
        datosVehiculo.setAlignment(Pos.CENTER_LEFT);

        Label marcaModelo = new Label(v.getMarca() + " " + v.getModelo());
        marcaModelo.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

        Label kilometros = new Label("Kilómetros: " + v.getKm());
        Label anio = new Label("Año: " + v.getAnio());

        datosVehiculo.getChildren().addAll(marcaModelo, kilometros, anio);
        return datosVehiculo;
    }

    private Button crearBotonSaberMas(VehiculoDto v) {
        Button boton = new Button("Saber más");
        boton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-background-radius: 5;");
        boton.setOnAction(e -> {
            // Acción del botón
            System.out.println("Ver más de: " + v.getMarca() + " " + v.getModelo());
        });
        return boton;
    }


    private void textoBienvenida() {
        descripcionConcesionario.setWrapText(true);
        descripcionConcesionario.setMaxWidth(Double.MAX_VALUE);
        descripcionConcesionario.setText(" En GearBorn Motors nos comprometemos a ofrecer vehículos de calidad " +
                "con un servicio fiable, seguro y transparente.\n Nuestro objetivo es brindar confianza en cada compra," +
                " garantizando atención personalizada y una experiencia excepcional para cada cliente.");
    }


    public List<VehiculoDto> vehiculos() {
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

                // Usamos Gson para parsear
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
}
