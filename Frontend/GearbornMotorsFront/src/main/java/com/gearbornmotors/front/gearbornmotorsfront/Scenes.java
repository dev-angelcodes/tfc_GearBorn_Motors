package com.gearbornmotors.front.gearbornmotorsfront;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Scenes {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LauncherApp.class.getResource("fxml/Login.fxml"));
            Parent newRoot = fxmlLoader.load();

            // Obtener tamaño de pantalla
            Rectangle2D limitePantalla = Screen.getPrimary().getVisualBounds();
            double ancho = limitePantalla.getWidth() * 0.8;
            double alto = limitePantalla.getHeight() * 0.8;

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Centrar la ventana
            stage.setX(limitePantalla.getMinX() + (limitePantalla.getWidth() - ancho) / 2);
            stage.setY(limitePantalla.getMinY() + (limitePantalla.getHeight() - alto) / 2);

            // Crear la transición de desvanecimiento
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.15), stage.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                // Crear la nueva escena
                Scene newScene = new Scene(newRoot, ancho, alto);

                // Aplicar efecto fade-in a la nueva escena
                newRoot.setOpacity(0);
                stage.setScene(newScene);

                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), newRoot);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();

        } catch (IOException e) {
            System.err.println("Error al cambiar de escena:\n " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void goConcesionario(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LauncherApp.class.getResource("fxml/Concesionario.fxml"));
            Parent newRoot = fxmlLoader.load();

            // Obtener tamaño de pantalla
            Rectangle2D limitePantalla = Screen.getPrimary().getVisualBounds();
            double ancho = limitePantalla.getWidth() * 0.8;
            double alto = limitePantalla.getHeight() * 0.8;

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Centrar la ventana
            stage.setX(limitePantalla.getMinX() + (limitePantalla.getWidth() - ancho) / 2);
            stage.setY(limitePantalla.getMinY() + (limitePantalla.getHeight() - alto) / 2);

            // Crear la transición de desvanecimiento
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.15), stage.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                // Crear la nueva escena
                Scene newScene = new Scene(newRoot, ancho, alto);

                // Aplicar efecto fade-in a la nueva escena
                newRoot.setOpacity(0);
                stage.setScene(newScene);

                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), newRoot);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();

        } catch (IOException e) {
            System.err.println("Error al cambiar de escena:\n " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void goRegister(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LauncherApp.class.getResource("fxml/Registro.fxml"));
            Parent newRoot = fxmlLoader.load();

            // Obtener tamaño de pantalla
            Rectangle2D limitePantalla = Screen.getPrimary().getVisualBounds();
            double ancho = limitePantalla.getWidth() * 0.8;
            double alto = limitePantalla.getHeight() * 0.8;

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Centrar la ventana
            stage.setX(limitePantalla.getMinX() + (limitePantalla.getWidth() - ancho) / 2);
            stage.setY(limitePantalla.getMinY() + (limitePantalla.getHeight() - alto) / 2);

            // Crear la transición de desvanecimiento
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.15), stage.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                // Crear la nueva escena
                Scene newScene = new Scene(newRoot, ancho, alto);

                // Aplicar efecto fade-in a la nueva escena
                newRoot.setOpacity(0);
                stage.setScene(newScene);

                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), newRoot);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();

        } catch (IOException e) {
            System.err.println("Error al cambiar de escena:\n " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void goMenu(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LauncherApp.class.getResource("fxml/Menu.fxml"));
            Parent newRoot = fxmlLoader.load();

            // Obtener tamaño de pantalla
            Rectangle2D limitePantalla = Screen.getPrimary().getVisualBounds();
            double ancho = limitePantalla.getWidth() * 0.8;
            double alto = limitePantalla.getHeight() * 0.8;

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Centrar la ventana
            stage.setX(limitePantalla.getMinX() + (limitePantalla.getWidth() - ancho) / 2);
            stage.setY(limitePantalla.getMinY() + (limitePantalla.getHeight() - alto) / 2);

            // Crear la transición de desvanecimiento
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.15), stage.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                // Crear la nueva escena
                Scene newScene = new Scene(newRoot, ancho, alto);

                // Aplicar efecto fade-in a la nueva escena
                newRoot.setOpacity(0);
                stage.setScene(newScene);

                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), newRoot);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();

        } catch (IOException e) {
            System.err.println("Error al cambiar de escena:\n " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void goPanelControl(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LauncherApp.class.getResource("fxml/PanelControl.fxml"));
            Parent newRoot = fxmlLoader.load();

            // Obtener tamaño de pantalla
            Rectangle2D limitePantalla = Screen.getPrimary().getVisualBounds();
            double ancho = limitePantalla.getWidth() * 0.8;
            double alto = limitePantalla.getHeight() * 0.8;

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Centrar la ventana
            stage.setX(limitePantalla.getMinX() + (limitePantalla.getWidth() - ancho) / 2);
            stage.setY(limitePantalla.getMinY() + (limitePantalla.getHeight() - alto) / 2);

            // Crear la transición de desvanecimiento
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.15), stage.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                // Crear la nueva escena
                Scene newScene = new Scene(newRoot, ancho, alto);

                // Aplicar efecto fade-in a la nueva escena
                newRoot.setOpacity(0);
                stage.setScene(newScene);

                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), newRoot);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();

        } catch (IOException e) {
            System.err.println("Error al cambiar de escena:\n " + e.getMessage());
            e.printStackTrace();
        }
    }
}