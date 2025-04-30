package com.gearbornmotors.front.gearbornmotorsfront.Controller;


import com.gearbornmotors.front.gearbornmotorsfront.Scenes;
import com.gearbornmotors.front.gearbornmotorsfront.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginController {

    @FXML public TextField usuario;
    @FXML public PasswordField contrasena;
    @FXML public MenuButton rolLogin;
    @FXML public MenuItem clienteItem;
    @FXML public MenuItem empleadoItem;
    @FXML public Label labelUsuario;
    @FXML public Button botonEmpleado;
    @FXML public Button botonCliente;



    public void initialize() {
        clienteItem.setOnAction(event -> {
            rolLogin.setText("Tipo de Usuario: Cliente");
            labelUsuario.setText("\uD83D\uDC64Email ➤");
            botonEmpleado.setVisible(false); botonEmpleado.setManaged(false);
            botonCliente.setVisible(true); botonCliente.setManaged(true);
            usuario.setPromptText("Introduzca el email");
        });

        empleadoItem.setOnAction(event -> {
            rolLogin.setText("Tipo de Usuario: Empleado");
            labelUsuario.setText("\uD83D\uDC64ID ➤");
            botonCliente.setVisible(false); botonCliente.setManaged(false);
            botonEmpleado.setVisible(true); botonEmpleado.setManaged(true);
            usuario.setPromptText("Introduzca el ID");
        });
    }

    public void InicioCliente(ActionEvent event) {
        String email = this.usuario.getText();
        String contrasena = md5(this.contrasena.getText());

        try {
            URL url = new URL("http://localhost:8080/gearBorn/api/cliente/login");


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            String jsonInput = String.format("{\"email\":\"%s\",\"contrasenaHasheada\":\"%s\"}", email, contrasena);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int status = con.getResponseCode();
            con.disconnect();

            if (status == 200) {
                // Solo si el login fue exitoso (200), se cambia la escena
                Scenes escena = new Scenes();
                escena.goConcesionario(event);
            } else if (status == 401) {
                System.out.println("Email o contraseña incorrectos");
                // Mostrar alerta si querés
            } else {
                System.out.println("Error al conectar con el servidor");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void InicioEmpleado(ActionEvent event) {
        try {
            int id = Integer.parseInt(this.usuario.getText().trim());
            String contrasena = md5(this.contrasena.getText().trim());

            URL url = new URL("http://localhost:8080/gearBorn/api/empleado/login");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            String jsonInput = String.format("{\"id\":%d,\"contrasenaHasheada\":\"%s\"}", id, contrasena);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int status = con.getResponseCode();
            con.disconnect();

            if (status == 200) {
                Session.getInstance().setEmpleadoId(id);

                // Login exitoso → cambiar de escena
                Scenes escena = new Scenes();
                escena.goPanelControl(event);
            } else if (status == 401) {
                System.out.println("ID o contraseña incorrectos");
                // Acá podrías mostrar una alerta
            } else {
                System.out.println("Error al conectar con el servidor. Código: " + status);
            }

        } catch (NumberFormatException e) {
            System.out.println("ID inválido: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void IrMenu(ActionEvent event) {
        Scenes escena = new Scenes();
        escena.goMenu(event);
    }

    public void IrRegistro(ActionEvent event) {
        Scenes scenes = new Scenes();
        scenes.goRegister(event);
    }
}