package com.gearbornmotors.front.gearbornmotorsfront;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LauncherApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LauncherApp.class.getResource("fxml/Menu.fxml"));
        stage.setTitle("Gearborn Motors");

        // Obtener tamaño de pantalla
        Rectangle2D limitePantalla = Screen.getPrimary().getVisualBounds();
        double ancho = limitePantalla.getWidth() * 0.8;
        double alto = limitePantalla.getHeight() * 0.8;

        // Configurar la escena
        Scene scene = new Scene(fxmlLoader.load(), ancho, alto);
        stage.setScene(scene);

        // Centrar la ventana
        stage.setX(limitePantalla.getMinX() + (limitePantalla.getWidth() - ancho) / 2);
        stage.setY(limitePantalla.getMinY() + (limitePantalla.getHeight() - alto) / 2);


        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/logo.png")));
            stage.getIcons().add(icon);
        } catch (NullPointerException e) {
            System.err.println("Error al cargar el icono.");
        }


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}