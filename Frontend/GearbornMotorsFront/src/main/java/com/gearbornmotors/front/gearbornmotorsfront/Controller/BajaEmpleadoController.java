package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.Empleado.EmpleadoDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BajaEmpleadoController {

    @FXML
    private VBox empleadosVBox;

    public void initialize() {
        cargarEmpleados();
    }

    private void cargarEmpleados() {
        List<EmpleadoDto> empleados = obtenerEmpleados();

        for (EmpleadoDto empleado : empleados) {
            HBox hBoxEmpleado = crearHBoxEmpleado(empleado);
            empleadosVBox.getChildren().add(hBoxEmpleado);
        }
    }

    private HBox crearHBoxEmpleado(EmpleadoDto empleado) {
        HBox hbox = new HBox(15);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefHeight(80);
        hbox.setStyle("-fx-padding: 10; -fx-background-color: #e8f0fe; -fx-border-color: #bbb; " +
                "-fx-border-radius: 8; -fx-background-radius: 8;");

        Label nombre = new Label("Nombre: " + empleado.getNombre());
        nombre.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        Label correo = new Label("Correo: " + empleado.getEmail());
        correo.setStyle("-fx-font-size: 13;");

        VBox datos = new VBox(5, nombre, correo);
        datos.setAlignment(Pos.CENTER_LEFT);

        Button bajaBtn = crearBotonBaja(empleado, hbox);

        hbox.getChildren().addAll(datos, bajaBtn);
        return hbox;
    }

    private Button crearBotonBaja(EmpleadoDto empleado, HBox hbox) {
        Button bajaBtn = new Button("Dar de baja");
        bajaBtn.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");

        bajaBtn.setOnAction(event -> {
            String email = empleado.getEmail();
            String url = "http://localhost:8080/gearBorn/api/empleado/suspenderEmpleado/" + URLEncoder.encode(email, StandardCharsets.UTF_8);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> {
                        if (response.statusCode() == 200) {
                            System.out.println("Empleado suspendido correctamente: " + email);
                            Platform.runLater(() -> empleadosVBox.getChildren().remove(hbox));
                        } else {
                            System.err.println("Error al suspender empleado: " + response.statusCode());
                        }
                    })
                    .exceptionally(e -> {
                        e.printStackTrace();
                        return null;
                    });
        });

        return bajaBtn;
    }


    private List<EmpleadoDto> obtenerEmpleados() {
        List<EmpleadoDto> empleados = new ArrayList<>();
        try {
            String url = "http://localhost:8080/gearBorn/api/empleado/getComercialesVenta";

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
                Type tipoLista = new TypeToken<List<EmpleadoDto>>() {}.getType();
                empleados = gson.fromJson(json, tipoLista);
            } else {
                System.out.println("Error al obtener empleados: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return empleados;
    }
}
