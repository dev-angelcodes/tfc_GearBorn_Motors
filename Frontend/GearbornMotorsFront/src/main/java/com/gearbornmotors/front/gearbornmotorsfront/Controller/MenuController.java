package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {
    private final Scenes escenas = new Scenes();

    @FXML
    public Label presentation;

    public void initialize() {
        presentation.setText("En GearBorn Motors no solo vendemos vehículos, conectamos personas con su próximo destino. " +
                "Te acompañamos en cada paso: ya sea que estés comprando tu primer coche, vendiendo uno de confianza o buscando una nueva experiencia al volante.");
    }


    public void goLogin(ActionEvent event) {
        escenas.goLogin(event);
    }
}