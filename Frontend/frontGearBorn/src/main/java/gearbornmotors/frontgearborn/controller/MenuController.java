package gearbornmotors.frontgearborn.controller;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuController {


    public ImageView logo;
    public Label presentation;

    public void initialize() {
        // Indicar posicion del user
        System.out.println("-> Menu");

        // Cargar la imagen del logo
        Image img = new Image(String.valueOf(getClass().getResource("/gearbornmotors/frontgearborn/img/logo.png")));
        logo.setPreserveRatio(true);
        logo.setFitHeight(150);
        logo.setFitWidth(200);
        logo.setImage(img);

        //Cargar Label
        presentation.setText("En GearBorn Motors no solo vendemos vehículos, conectamos personas con su próximo destino. " +
                "Te acompañamos en cada paso: ya sea que estés comprando tu primer coche, vendiendo uno de confianza o buscando una nueva experiencia al volante.");

    }
}
