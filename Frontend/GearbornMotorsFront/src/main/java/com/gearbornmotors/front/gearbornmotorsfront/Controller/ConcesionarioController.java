package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente.ClienteDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Vehiculo.VehiculoDto;
import com.gearbornmotors.front.gearbornmotorsfront.Scenes;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ConcesionarioController {

    private ClienteDto cliente;


    @FXML public Label descripcionConcesionario;
    @FXML public VBox vehiculosContainer;
    @FXML public MenuButton buscarPorMarca;
    @FXML public MenuButton buscarPorModelo;


    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
        System.out.println("Cliente recibido: " + cliente.getNombre());
    }



    @FXML
    public void initialize() {
        textoBienvenida();
        cargarVehiculos(vehiculos());
        actualizarMarcasModelos();
    }

    @FXML
    public void mostrarTodosLosVehiculos(ActionEvent event) {
        buscarPorModelo.getItems().clear();

        buscarPorMarca.setText("Marcas");
        buscarPorModelo.setText("Modelos");
        vehiculosContainer.getChildren().clear();

        cargarVehiculos(vehiculos());
    }

    @FXML
    public void cargarMenu(ActionEvent event) {
        Scenes escena = new Scenes();
        escena.goMenu(event);
    }

    public void obtenerVehiculosFiltradosDesdeApi(String marca, String modelo) {
        try {
            // Codificar los parámetros para que no den error en la URL
            String url = "http://localhost:8080/gearBorn/api/vehiculo/getVehiculosFiltrados?marca=" +
                    URLEncoder.encode(marca, StandardCharsets.UTF_8);

            if (modelo != null && !modelo.isBlank()) {
                url += "&modelo=" + URLEncoder.encode(modelo, StandardCharsets.UTF_8);
            }

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(json -> {
                        try {
                            Gson gson = new Gson();
                            VehiculoDto[] array = gson.fromJson(json, VehiculoDto[].class);
                            List<VehiculoDto> filtrados = List.of(array);

                            Platform.runLater(() -> {
                                vehiculosContainer.getChildren().clear();
                                cargarVehiculos(filtrados);

                                if (filtrados.isEmpty()) {
                                    System.out.println("No se encontraron vehículos con los filtros seleccionados.");
                                    // Aquí puedes mostrar una alerta si quieres
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al codificar la URL");
        }
    }


    private void actualizarMarcasModelos() {
        obtenerMarcasDesdeApi().thenAccept(marcas -> {
            Platform.runLater(() -> {
                actualizarMenuButtonConMarcas(buscarPorMarca, marcas);
            });
        });
    }

    private void actualizarMenuButtonConMarcas(MenuButton buscarPorMarca, List<String> marcas) {
        buscarPorMarca.getItems().clear();

        for (String marca : marcas) {
            MenuItem item = new MenuItem(marca);
            item.setOnAction(e -> {
                buscarPorMarca.setText(marca);
                buscarPorModelo.setText("Modelos");
                actualizarMenuButtonConModelos(marca);
                obtenerVehiculosFiltradosDesdeApi(marca, null); //cargamos los vehiculos de esa marca
            });
            buscarPorMarca.getItems().add(item);
        }
    }


    private void actualizarMenuButtonConModelos(String marca) {
        buscarPorModelo.getItems().clear();
        obtenerModelosDesdeApi(marca).thenAccept(modelos -> {
            Platform.runLater(() -> {
                for (String modelo : modelos) {
                    MenuItem item = new MenuItem(modelo);
                    item.setOnAction(e -> {
                        buscarPorModelo.setText(modelo);
                        //Al seleccionar un modelo, filtramos automáticamente por marca + modelo
                        obtenerVehiculosFiltradosDesdeApi(marca, modelo);
                    });
                    buscarPorModelo.getItems().add(item);
                }
            });
        });
    }



    public CompletableFuture<List<String>> obtenerModelosDesdeApi(String marca) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "http://localhost:8080/gearBorn/api/vehiculo/getModelos/" + marca;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        return mapper.readValue(json, new TypeReference<List<String>>() {});
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Collections.emptyList();
                    }
                });
    }


    public CompletableFuture<List<String>> obtenerMarcasDesdeApi() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/gearBorn/api/vehiculo/getMarcas"))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        return mapper.readValue(json, new TypeReference<List<String>>() {});
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Collections.emptyList();
                    }
                });
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
        hbox.setStyle("-fx-padding: 15; -fx-background-color: #f2f2f2; -fx-border-color: #ccc; " +
                "-fx-border-radius: 10; -fx-background-radius: 10;");
        hbox.setPrefHeight(150);
        hbox.setAlignment(Pos.CENTER_LEFT);

        ImageView imagen = crearImagenVehiculo(v);

        // VBox para los datos principales
        VBox datos = crearVBoxDatosVehiculo(v);
        datos.setAlignment(Pos.CENTER_LEFT);

        // VBox para el botón (alineado a la derecha)
        VBox botonContainer = new VBox();
        botonContainer.setAlignment(Pos.CENTER_RIGHT);
        botonContainer.setPadding(new Insets(0, 0, 0, 50)); // Espacio a la izquierda
        Button boton = crearBotonSaberMas(v);
        botonContainer.getChildren().add(boton);

        hbox.getChildren().addAll(imagen, datos, botonContainer);
        HBox.setHgrow(datos, Priority.ALWAYS); // Hace que los datos ocupen el espacio disponible

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
        VBox datosVehiculo = new VBox(8); // Menor espaciado
        datosVehiculo.setAlignment(Pos.CENTER_LEFT);

        // Usar GridPane para alinear las etiquetas
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        Label marcaModelo = new Label(v.getMarca() + " " + v.getModelo());
        marcaModelo.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

        Label kilometros = new Label("Kilómetros:");
        kilometros.setStyle("-fx-font-weight: bold;");
        Label valorKm = new Label(String.format("%,.1f", v.getKm()));

        Label anio = new Label("Año:");
        anio.setStyle("-fx-font-weight: bold;");
        Label valorAnio = new Label(String.valueOf(v.getAnio()));

        // Añadir al grid
        grid.add(marcaModelo, 0, 0, 2, 1); // Ocupa 2 columnas

        grid.add(kilometros, 0, 1);
        grid.add(valorKm, 1, 1);

        grid.add(anio, 0, 2);
        grid.add(valorAnio, 1, 2);

        datosVehiculo.getChildren().add(grid);
        return datosVehiculo;
    }

    private Button crearBotonSaberMas(VehiculoDto v) {
        Button boton = new Button("Saber más");
        boton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; " +
                "-fx-background-radius: 5; -fx-padding: 8 15;");
        boton.setOnAction(e -> {
            Scenes escena = new Scenes();
            escena.goVentaCliente(e, cliente);
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
