package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente.ClienteDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Empleado.EmpleadoDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Gastos.GastoDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Vehiculo.VehiculoDto;
import com.gearbornmotors.front.gearbornmotorsfront.Scenes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CompraClienteControler {

    private EmpleadoDto comercialSeleccionado = null;
    private ClienteDto cliente;
    private VehiculoDto vehiculo;

    @FXML
    public ImageView imgVehiculo;
    @FXML
    public Label marca, importeCompra, fechaCompra, modelo, tipo, anho, km, tipoCombustible, tipoCambio, color, tituloEscena, nombreCliente;
    @FXML public VBox EmpleadosContainer;


    public void setCliente(ClienteDto cliente) {
        nombreCliente.setText(cliente.nombre);
        this.cliente = cliente;
    }
    public void setVehiculo(VehiculoDto vehiculo) {
        this.vehiculo = vehiculo;

        tituloEscena.setText(vehiculo.getMarca() + " " + vehiculo.getModelo());
        marca.setText("Marca: " + vehiculo.getMarca());
        modelo.setText("Modelo: " + vehiculo.getModelo());
        tipo.setText("Tipo: " + vehiculo.getTipo());
        anho.setText("Año: " + vehiculo.getAnio());
        km.setText("Kilometros: " + vehiculo.getKm());
        tipoCombustible.setText("Tipo de combustible: " + vehiculo.getTipoCombustible());
        tipoCambio.setText("Tipo de cambio: " + vehiculo.getTipoCambio());
        color.setText("Color: " + vehiculo.getColor());
        importeCompra.setText("Precio: €" + calculoPrecio());

    }


    public void initialize() {
        fechaCompra.setText("Fecha de la transacción: " + java.time.LocalDate.now());
        cargarEmpleados();
    }

    private double calculoPrecio() {
        System.out.println("Matrícula recibida: " + vehiculo.getMatricula());

        List<GastoDto> gastos = obtenerGastosPorMatricula(vehiculo.getMatricula());

        double totalGastos = 0;
        for (GastoDto gasto : gastos) {
            System.out.println(gasto.getImporte());
            totalGastos += gasto.getImporte();
        }

         return totalGastos;
    }

    private List<GastoDto> obtenerGastosPorMatricula(String matricula) {
        List<GastoDto> lista = new ArrayList<>();
        try {
            String endpoint = "http://localhost:8080/gearBorn/api/gasto/getGastosByMatricula/" + matricula;
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String json = reader.lines().reduce("", String::concat);

                Gson gson = new Gson(); // Sin adaptadores especiales
                Type tipoLista = new TypeToken<List<GastoDto>>() {}.getType();
                lista = gson.fromJson(json, tipoLista);

                reader.close();
            } else {
                System.out.println("Error al obtener gastos: " + conn.getResponseCode());
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
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
