package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.Empleado.EmpleadoDto;
import com.gearbornmotors.front.gearbornmotorsfront.Scenes;
import com.gearbornmotors.front.gearbornmotorsfront.Session;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.stream.Collectors;

public class PanelControlController {

    protected final LoginController loginController = new LoginController();

    @FXML  public StackPane contenidoControl;


    @FXML
    public void initialize() throws IOException {
        datosEmpleadoLogueado();
    }


    @FXML
    public void mostrarRegistro(ActionEvent event) {
        try {
            Parent registroEmpleado = FXMLLoader.load(Objects.requireNonNull(getClass()
                    .getResource("/com/gearbornmotors/front/gearbornmotorsfront/fxml/RegistroEmpleado.fxml")));
            contenidoControl.getChildren().setAll(registroEmpleado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mostrarGastos(ActionEvent event) {
        try {
            Parent registroEmpleado = FXMLLoader.load(Objects.requireNonNull(getClass()
                    .getResource("/com/gearbornmotors/front/gearbornmotorsfront/fxml/Gastos.fxml")));
            contenidoControl.getChildren().setAll(registroEmpleado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cargarMenu(ActionEvent event) {
        Scenes scenes = new Scenes();
        scenes.goMenu(event);
    }

    @FXML
    public void registrarCompra(ActionEvent event) {
        try {
            Parent registroEmpleado = FXMLLoader.load(Objects.requireNonNull(getClass()
                    .getResource("/com/gearbornmotors/front/gearbornmotorsfront/fxml/CompraVehiculo.fxml")));
            contenidoControl.getChildren().setAll(registroEmpleado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected EmpleadoDto datosEmpleadoLogueado() throws IOException {
        int idEmpleado = Session.getInstance().getEmpleadoId();

        String url = "http://localhost:8080/gearBorn/api/empleado/getEmpleadoById/" + idEmpleado;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response = in.lines().collect(Collectors.joining());

                // Deserializar a DTO
                EmpleadoDto empleado = new Gson().fromJson(response, EmpleadoDto.class);
                return empleado;
            }
        } else {
            System.out.println("Error al obtener el empleado: código " + responseCode);
            return null;
        }
    }
}
