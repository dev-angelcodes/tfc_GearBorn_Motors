package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class PanelControlController {

    private final LoginController loginController = new LoginController();

    @FXML  public StackPane contenidoControl;


    public void initialize(){
        int idEmpleado = loginController.getIdUsuarioLogueado();
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
}
