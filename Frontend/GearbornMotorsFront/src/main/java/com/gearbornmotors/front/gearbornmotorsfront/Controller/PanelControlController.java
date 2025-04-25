package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class PanelControlController {

    @FXML  public StackPane contenidoControl;


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
}
