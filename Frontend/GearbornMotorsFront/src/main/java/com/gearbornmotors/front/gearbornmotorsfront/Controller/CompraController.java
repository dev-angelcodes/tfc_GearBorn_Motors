package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CompraController {

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




    @FXML
    public void seleccionarImagen(javafx.event.ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File archivoSeleccionado = fileChooser.showOpenDialog(stage);

        if (archivoSeleccionado != null) {
            String nombreArchivo = archivoSeleccionado.getName();

            mostrarImagen(archivoSeleccionado, nombreArchivo);
            /*guardarImg(archivoSeleccionado);*/
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
            Path destino = Path.of("src/main/resources/com/gearbornmotors/front/gearbornmotorsfront/img/" + archivoSeleccionado.getName());
            Files.copy(archivoSeleccionado.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
