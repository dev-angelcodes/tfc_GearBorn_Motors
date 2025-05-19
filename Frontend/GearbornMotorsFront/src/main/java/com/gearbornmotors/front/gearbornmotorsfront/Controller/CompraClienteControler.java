package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente.ClienteDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Empleado.EmpleadoDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Vehiculo.VehiculoDto;
import com.gearbornmotors.front.gearbornmotorsfront.Scenes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CompraClienteControler {

    private EmpleadoDto comercialSeleccionado = null;
    private ClienteDto cliente;
    private VehiculoDto vehiculo;

    @FXML public VBox EmpleadosContainer;
    @FXML public Label tituloEscena;
    @FXML public Label nombreCliente;


    public void setCliente(ClienteDto cliente) {
        nombreCliente.setText(cliente.nombre);
        this.cliente = cliente;
    }
    public void setVehiculo(VehiculoDto vehiculo) {
        tituloEscena.setText(vehiculo.getMarca() + " " + vehiculo.getModelo());
        this.vehiculo = vehiculo;
    }

    public void initialize() {
        cargarEmpleados();
    }

    private void cargarEmpleados() {
        List<EmpleadoDto> empleados = obtenerEmpleados();

        for (EmpleadoDto empleado : empleados) {
            HBox hBoxEmpleado = crearHBoxEmpleado(empleado);
            EmpleadosContainer.getChildren().add(hBoxEmpleado);
        }

    }

    private HBox crearHBoxEmpleado(EmpleadoDto empleado) {
        HBox hbox = new HBox(15);
        hbox.setStyle("-fx-padding: 10; -fx-background-color: #e8f0fe; -fx-border-color: #bbb; " +
                "-fx-border-radius: 8; -fx-background-radius: 8;");
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefHeight(80);

        Label nombre = new Label("Nombre: " + empleado.getNombre());
        nombre.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        Label correo = new Label("Correo: " + empleado.getEmail());
        correo.setStyle("-fx-font-size: 13;");

        VBox datos = new VBox(5, nombre, correo);
        datos.setAlignment(Pos.CENTER_LEFT);

        hbox.getChildren().add(datos);

        // Evento de selección al hacer clic
        hbox.setOnMouseClicked(event -> {
            comercialSeleccionado = empleado;
            actualizarEstilosSeleccion(empleado);
            System.out.println("Comercial seleccionado: " + empleado.getNombre());
        });

        return hbox;
    }

    private void actualizarEstilosSeleccion(EmpleadoDto seleccionado) {
        for (javafx.scene.Node nodo : EmpleadosContainer.getChildren()) {
            if (nodo instanceof HBox) {
                HBox hbox = (HBox) nodo;
                Label label = (Label) ((VBox) hbox.getChildren().get(0)).getChildren().get(0); // Asume que primer Label es nombre
                String nombreTexto = label.getText().replace("Nombre: ", "").trim();

                if (nombreTexto.equals(seleccionado.getNombre())) {
                    hbox.setStyle("-fx-padding: 10; -fx-background-color: #cce5ff; -fx-border-color: #007bff; " +
                            "-fx-border-radius: 8; -fx-background-radius: 8;");
                } else {
                    hbox.setStyle("-fx-padding: 10; -fx-background-color: #e8f0fe; -fx-border-color: #bbb; " +
                            "-fx-border-radius: 8; -fx-background-radius: 8;");
                }
            }
        }
    }


    public void volverConcesionario(ActionEvent event) {
        Scenes escena = new Scenes();
        escena.goConcesionario(event, cliente);
    }


    public List<EmpleadoDto> obtenerEmpleados() {
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

                // Usamos Gson para parsear
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
