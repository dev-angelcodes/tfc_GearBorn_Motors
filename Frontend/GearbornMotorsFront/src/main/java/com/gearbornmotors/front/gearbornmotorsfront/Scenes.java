package com.gearbornmotors.front.gearbornmotorsfront;

import com.gearbornmotors.front.gearbornmotorsfront.Controller.CompraClienteControler;
import com.gearbornmotors.front.gearbornmotorsfront.Controller.ConcesionarioController;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente.ClienteDto;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Vehiculo.VehiculoDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Scenes {
    private void cambiarEscena(String fxmlRuta, ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LauncherApp.class.getResource(fxmlRuta));
            Parent root = fxmlLoader.load();

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double ancho = screenBounds.getWidth();
            double alto = screenBounds.getHeight();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(screenBounds.getMinX());
            stage.setY(screenBounds.getMinY());
            stage.setScene(new Scene(root, ancho, alto));
            stage.show();

        } catch (IOException e) {
            System.err.println("Error al cambiar de escena:\n " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void goLogin(ActionEvent event) {
        cambiarEscena("fxml/Login.fxml", event);
    }

    public void goConcesionario(ActionEvent event, ClienteDto cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(LauncherApp.class.getResource("fxml/Concesionario.fxml"));
            Parent root = loader.load();

            // Pasar el cliente al controlador
            ConcesionarioController controller = loader.getController();
            controller.setCliente(cliente);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, screenBounds.getWidth(), screenBounds.getHeight()));
            stage.show();

        } catch (IOException e) {
            System.err.println("Error al cambiar a la escena de Concesionario:\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void goRegister(ActionEvent event) {
        cambiarEscena("fxml/RegistroCliente.fxml", event);
    }

    public void goMenu(ActionEvent event) {
        cambiarEscena("fxml/Menu.fxml", event);
    }

    public void goPanelControl(ActionEvent event) {
        cambiarEscena("fxml/PanelControl.fxml", event);
    }

    public void goVentaCliente(ActionEvent event, ClienteDto cliente, VehiculoDto vehiculo) {
        try {
            FXMLLoader loader = new FXMLLoader(LauncherApp.class.getResource("fxml/clienteCompraVehiculo.fxml"));
            Parent root = loader.load();

            // Obtener el controller y pasarle el cliente
            CompraClienteControler controller = loader.getController();
            controller.setCliente(cliente);
            controller.setVehiculo(vehiculo);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, screenBounds.getWidth(), screenBounds.getHeight()));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
