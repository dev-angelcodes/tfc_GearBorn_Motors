package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente.ClienteDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente.IdClienteDto;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompraClienteControler {


    private EmpleadoDto comercialSeleccionado = null;
    private ClienteDto cliente;
    private VehiculoDto vehiculo;

    @FXML
    public ImageView imgVehiculo;
    @FXML
    public Label garantia;
    @FXML
    public Label marca, importeCompra, fechaCompra, modelo, tipo, anho, km, tipoCombustible, tipoCambio, color, tituloEscena;
    @FXML public VBox EmpleadosContainer;


    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }
    public void setVehiculo(VehiculoDto vehiculo) {
        this.vehiculo = vehiculo;

        tituloEscena.setText(vehiculo.getMarca() + " " + vehiculo.getModelo());
        marca.setText(vehiculo.getMarca());
        modelo.setText(vehiculo.getModelo());
        tipo.setText(vehiculo.getTipo());
        anho.setText(String.valueOf(vehiculo.getAnio()));
        km.setText(String.valueOf(vehiculo.getKm()));
        tipoCombustible.setText(vehiculo.getTipoCombustible());
        tipoCambio.setText(vehiculo.getTipoCambio());

        String colorHex = vehiculo.getColor();
        String cssColor = "#" + colorHex.substring(2);
        color.setStyle("-fx-background-color: " + cssColor + "; -fx-min-width: 50px; -fx-min-height: 20px;" +
                            "-fx-border-color: black; -fx-border-radius: 4px;");

        importeCompra.setText(calculoPrecio() + " €");

        cargarImagenVehiculo(vehiculo.getImg());
    }

    private void cargarImagenVehiculo(String nombreImg) {
        String path = "/com/gearbornmotors/front/gearbornmotorsfront/img/" + nombreImg;
        InputStream imageStream = getClass().getResourceAsStream(path);

        if (imageStream == null) {
            System.err.println("Imagen no encontrada en: " + path + " — Usando imagen por defecto.");
            imageStream = getClass().getResourceAsStream("/com/gearbornmotors/front/gearbornmotorsfront/img/imgDefaultVehiculo.png");
        }

        if (imageStream != null) {
            imgVehiculo.setImage(new Image(imageStream));
        } else {
            System.err.println("No se pudo cargar ninguna imagen.");
        }
    }


    public void initialize() {
        fechaCompra.setText(String.valueOf(java.time.LocalDate.now()));
        garantia.setText(String.valueOf(LocalDate.now().plusYears(2)));
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
        totalGastos = anadirGastos(totalGastos);
         return totalGastos;
    }

    private Double anadirGastos(double totalGastos) {
        double beneficio;

        if (totalGastos < 10000) {
            beneficio = totalGastos * 0.30;
        } else if (totalGastos < 20000) {
            beneficio = totalGastos * 0.25;
        } else if (totalGastos < 40000) {
            beneficio = totalGastos * 0.20;
        } else {
            beneficio = totalGastos * 0.15;
        }

        return totalGastos + beneficio;
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
        hbox.getStyleClass().add("hbox-empleado"); // Aplica el estilo CSS
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefHeight(80);

        Label nombre = new Label("Nombre: " + empleado.getNombre());
        nombre.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        Label correo = new Label("Correo: " + empleado.getEmail());
        correo.setStyle("-fx-font-size: 13;");

        VBox datos = new VBox(5, nombre, correo);
        datos.setAlignment(Pos.CENTER_LEFT);

        hbox.getChildren().add(datos);

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

    @FXML
    public void realizarCompra(ActionEvent event) {
        System.out.println(cliente.toString());
        System.out.println(vehiculo.toString());
        System.out.println(comercialSeleccionado.toString());
    }
}
