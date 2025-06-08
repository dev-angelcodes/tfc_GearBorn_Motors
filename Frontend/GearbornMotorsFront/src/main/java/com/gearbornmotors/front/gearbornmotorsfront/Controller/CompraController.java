
package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Alerts.Alertas;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Empleado.EmpleadoDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Gastos.CompraGastoRequestDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Vehiculo.CompraVehiculoRequestDto;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CompraController extends PanelControlController {

    @FXML public ColorPicker colorPicker;
    @FXML public MenuButton tipoVehiculo;
    @FXML public MenuItem coche;
    @FXML public MenuItem moto;
    @FXML public TextField matricula;
    @FXML public TextField marca;
    @FXML public MenuButton estadoVehiculo;
    @FXML public MenuItem disponible;
    @FXML public MenuItem reservado;
    @FXML public MenuItem vendido;
    @FXML public MenuButton tipoCambio;
    @FXML public MenuItem manual;
    @FXML public MenuItem automatico;
    @FXML public MenuButton combustible;
    @FXML public MenuItem gasolina;
    @FXML public MenuItem diesel;
    @FXML public MenuItem electrico;
    @FXML public TextField anho;
    @FXML public TextField modelo;
    @FXML public Button botonSeleccionarImg;
    @FXML public Label nombreArchivoLabel;
    @FXML public ImageView previewImagen;
    @FXML public TextField precio;
    @FXML public TextField proveedor;
    @FXML public TextField km;


    private File imagenSeleccionada;


    @FXML
    public void initialize() {
        seleccionVehiculo();
        seleccionEstado();
        seleccionCambio();
        seleccionCombustible();
    }


    @FXML
    public void seleccionarImagen(javafx.event.ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        imagenSeleccionada = fileChooser.showOpenDialog(stage);

        if (imagenSeleccionada != null) {
            String nombreArchivo = imagenSeleccionada.getName();
            mostrarImagen(imagenSeleccionada, nombreArchivo);
        }
    }

    @FXML
    public void registrarVehiculo(ActionEvent event) {
        if(camposComprobados()){
            CompraVehiculoRequestDto vehiculo = vehiculoGuardado();
            enviarVehiculoApi(vehiculo);
            guardarImg(imagenSeleccionada);

        }else{
            Alertas.info( "Campos incompletos", "Faltan campos por completar",
                    "Por favor, complete todos los campos requeridos antes de continuar.");
        }
    }

    private void enviarVehiculoApi(CompraVehiculoRequestDto vehiculo) {
        Gson gson = new Gson();
        String json = gson.toJson(vehiculo);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/gearBorn/api/vehiculo/registrarVehiculo"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() == 201) {
                        Alertas.info( "Registro Exitoso", "Vehículo Registrado",
                                "El vehículo ha sido registrado correctamente.");
                        try {
                            CompraGastoRequestDto gasto = registrarGasto();
                            enviarGastoApi(gasto);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        Alertas.info( "Error al registrar vehículo", "Error de registro",
                                "No se pudo registrar el vehículo. Por favor, intente nuevamente.");
                        System.err.println("Error: " + response.statusCode() + " - " + response.body());
                    }
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
    }

    private CompraGastoRequestDto registrarGasto() throws IOException {
        EmpleadoDto empleadoDto = datosEmpleadoLogueado();
        int idVehiculo = obtenerIdVehiculo();
        
        CompraGastoRequestDto gasto = new CompraGastoRequestDto();
        gasto.setImporte(Double.parseDouble(precio.getText().trim()));
        gasto.setNombreProv(proveedor.getText().trim());
        gasto.setIdEmpleado(empleadoDto.getId());
        gasto.setIdVehiculo(idVehiculo);
        return gasto;
    }

    private int obtenerIdVehiculo() {
        try {
            String matriculaStr = matricula.getText().trim();
            String url = "http://localhost:8080/gearBorn/api/vehiculo/getIdByMatricula/" + matriculaStr;

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String idString = in.readLine();
                    return Integer.parseInt(idString);
                }
            } else {
                Alertas.info( "Error al obtener ID del vehículo", "Error de conexión",
                        "No se pudo obtener el ID del vehículo. Por favor, intente nuevamente.");
                System.out.println("Error al obtener ID del vehículo. Código: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }


    private void enviarGastoApi(CompraGastoRequestDto gasto) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(gasto);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/gearBorn/api/gasto/registrarGasto"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() == 201) {
                        Alertas.info( "Registro Exitoso", "Gasto Registrado",
                                "El gasto ha sido registrado correctamente.");
                    } else {
                        Alertas.error( "Error al registrar gasto", "Error de registro",
                                "No se pudo registrar el gasto. Por favor, intente nuevamente.");
                        System.err.println("Error al registrar gasto. Código: " + response.statusCode());
                        System.err.println("Mensaje: " + response.body());
                    }
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
    }


    private CompraVehiculoRequestDto vehiculoGuardado() {
        try{
            CompraVehiculoRequestDto v = new CompraVehiculoRequestDto();
            v.setMatricula(matricula.getText());
            v.setTipo(tipoVehiculo.getText());
            v.setMarca(marca.getText());
            v.setModelo(modelo.getText());
            v.setAnoFabricacion(Integer.parseInt(anho.getText()));
            v.setKm(Double.parseDouble(km.getText()));
            v.setEstado(estadoVehiculo.getText());
            v.setTipoCombustible(combustible.getText());
            v.setTipoCambio(tipoCambio.getText());
            v.setColor(colorPicker.getValue().toString());
            v.setImg(matricula.getText() + "-" + marca.getText() + ".png");
            return v;
        }catch (NumberFormatException e){
            Alertas.advertencia( "Año o Kilometraje inválido", "Error de formato",
                    "Por favor, introduzca un año de fabricación y kilómetros válidos.");
            return null;
        }
    }

    private void mostrarImagen(File archivo, String nombreArchivo) {

        nombreArchivoLabel.setVisible(true); nombreArchivoLabel.setManaged(true);
        previewImagen.setVisible(true); previewImagen.setManaged(true);

        nombreArchivoLabel.setText("Nombre del archivo: \n\"" + nombreArchivo + "\"");
        Image imagen = new Image(archivo.toURI().toString());
        previewImagen.setImage(imagen);
    }

    private void guardarImg(File archivoSeleccionado) {
        try {
            Path destino = Path.of("src/main/resources/com/gearbornmotors/front/gearbornmotorsfront/img/"
                    + (matricula.getText() + "-" + marca.getText() + ".png"));
            Files.copy(archivoSeleccionado.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean camposComprobados() {
        return !tipoVehiculo.getText().equals("Tipos de vehículos") && !matricula.getText().trim().isEmpty() && !marca.getText().trim().isEmpty()
                && !precio.getText().trim().isEmpty() && !estadoVehiculo.getText().equals("Estado") && !tipoCambio.getText().equals("Tipos de cambio")
                && !combustible.getText().equals("Tipos de combustible") && !anho.getText().trim().isEmpty()
                && !modelo.getText().trim().isEmpty() && !proveedor.getText().trim().isEmpty() && !km.getText().trim().isEmpty() ;
    }

    private void seleccionVehiculo() {
        coche.setOnAction(event -> {
            tipoVehiculo.setText("Coche");
        });
        moto.setOnAction(event -> {
            tipoVehiculo.setText("Moto");
        });
    }

    private void seleccionCombustible() {
        gasolina.setOnAction(event -> {
            combustible.setText("Gasolina");
        });
        diesel.setOnAction(event -> {
            combustible.setText("Diesel");
        });
        electrico.setOnAction(event -> {
            combustible.setText("Eléctrico");
        });
    }

    private void seleccionCambio() {
        manual.setOnAction(event -> {
            tipoCambio.setText("Manual");
        });
        automatico.setOnAction(event -> {
            tipoCambio.setText("Automático");
        });
    }

    private void seleccionEstado() {
        disponible.setOnAction(event -> {
            estadoVehiculo.setText("Disponible");
        });
        reservado.setOnAction(event -> {
            estadoVehiculo.setText("Reservado");
        });
        vendido.setOnAction(event -> {
            estadoVehiculo.setText("Vendido");
        });
    }
}
