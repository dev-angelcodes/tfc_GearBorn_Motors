package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginController {

    @FXML public TextField usuario;
    @FXML public PasswordField contrasena;


    public void VerificarEmail(ActionEvent event) {
        String email = this.usuario.getText();
        String contrasena = this.contrasena.getText();

        String contrasenaMD5 = md5(contrasena);
        System.out.println("Contraseña en MD5: " + contrasenaMD5);

        // Aqui tenemos que comparar la contraseña que vamos a obtener del api, con la contraseña introducida por el cliet

    }


    public static String md5(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(texto.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b)); // convierte a hex
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
