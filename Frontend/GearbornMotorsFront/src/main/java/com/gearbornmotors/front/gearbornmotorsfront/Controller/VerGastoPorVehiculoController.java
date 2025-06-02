package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.Vehiculo.VehiculoDto;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class VerGastoPorVehiculoController extends PanelControlController {

    @FXML
    private VBox contenidoPrincipal;

    @FXML
    public void initialize() throws IOException {
        cargarVehiculos();
    }

    private void cargarVehiculos() {
        List<VehiculoDto> vehiculos = obtenerVehiculos();

        VBox listaVehiculos = new VBox(10);
        for (VehiculoDto v : vehiculos) {
            HBox hbox = crearHBoxResumen(v);
            listaVehiculos.getChildren().add(hbox);
        }

        contenidoPrincipal.getChildren().setAll(listaVehiculos);
    }

    private List<VehiculoDto> obtenerVehiculos() {
        List<VehiculoDto> vehiculos = new ArrayList<>();
        try {
            String url = "http://localhost:8080/gearBorn/api/vehiculo/AllVehiculos";

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

    private HBox crearHBoxResumen(VehiculoDto v) {
        HBox hbox = new HBox(20);
        hbox.setStyle("-fx-padding: 15; -fx-background-color: #f2f2f2; -fx-border-color: #ccc; " +
                "-fx-border-radius: 10; -fx-background-radius: 10;");
        hbox.setPrefHeight(150);
        hbox.setAlignment(Pos.CENTER_LEFT);

        ImageView imagen = crearImagenVehiculo(v);
        VBox datosVehiculo = crearVBoxDatosVehiculo(v);
        Button botonVerGastos = crearBotonVerGastos(v);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        hbox.getChildren().addAll(imagen, datosVehiculo, spacer, botonVerGastos);
        return hbox;
    }

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

    private Button crearBotonVerGastos(VehiculoDto v) {
        Button btn = new Button("Ver gastos");
        btn.setOnAction(e -> mostrarGastosEnNuevaVista(v.getMatricula()));
        return btn;
    }

    private void mostrarGastosEnNuevaVista(String matricula) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/gearbornmotors/front/gearbornmotorsfront/fxml/gastosVehiculo.fxml"));
            VBox gastosView = loader.load();

            GastosVehiculoController controller = loader.getController();
            controller.cargarGastos(matricula);

            contenidoPrincipal.getChildren().setAll(gastosView);
        } catch (IOException e) {
            e.printStackTrace();
            contenidoPrincipal.getChildren().setAll(new Label("⚠ Error al cargar vista de gastos."));
        }
    }
}
