package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.ClienteDto;
import com.gearbornmotors.front.gearbornmotorsfront.Scenes;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginController {

    @FXML public TextField usuario;
    @FXML public PasswordField contrasena;
    @FXML public javafx.scene.control.TextArea respuestaJson;

    private ClienteDto clienteLogueado;


    public void VerificarEmail(ActionEvent event) {
        String email = this.usuario.getText();
        String contrasena = this.contrasena.getText();  //md5(this.contrasena.getText());

        try {
            URL url = new URL("http://localhost:8080/gearBorn/api/cliente/verificarLogin");

            // Crear conexión HTTP
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Crear cuerpo JSON
            String jsonInput = String.format("{\"email\":\"%s\",\"contrasena\":\"%s\"}", email, contrasena);

            // Enviar JSON al backend
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leer respuesta
            int status = con.getResponseCode();
            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
                in.close();

                // Parsear JSON a ClienteDto
                Gson gson = new Gson();
                clienteLogueado = gson.fromJson(content.toString(), ClienteDto.class);

                System.out.println("Login correcto: " + clienteLogueado.getNombre());

                respuestaJson.setText(clienteLogueado.toString());

                /*// Cambiar de escena
                Scenes escena = new Scenes();
                escena.goConcesionario(event);*/

            } else if (status == 401) {
                respuestaJson.setText("Email o contraseña incorrectos");
            } else {
                respuestaJson.setText("Error al conectar con el servidor");
            }

            con.disconnect();
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
}