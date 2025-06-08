package com.gearbornmotors.front.gearbornmotorsfront.Alerts;

public class Alertas {

    public static void error(String titulo, String cabercero, String contenido){
        javafx.scene.control.Alert a = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        a.setTitle(titulo);
        a.setHeaderText(cabercero);
        a.setContentText(contenido);
        a.showAndWait();
    }

    public static void info(String titulo, String cabercero, String contenido){
        javafx.scene.control.Alert a = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        a.setTitle(titulo);
        a.setHeaderText(cabercero);
        a.setContentText(contenido);
        a.showAndWait();
    }

    public static void advertencia(String titulo, String cabercero, String contenido){
        javafx.scene.control.Alert a = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        a.setTitle(titulo);
        a.setHeaderText(cabercero);
        a.setContentText(contenido);
        a.showAndWait();
    }
}
