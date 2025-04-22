package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.ClientRegisterRequestDto;
import com.gearbornmotors.front.gearbornmotorsfront.Scenes;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegistroController {

    @FXML public TextField email;
    @FXML public TextField dni;
    @FXML public TextField nombre;
    @FXML public TextField apellidos;
    @FXML public TextField telefono;
    @FXML public TextField direccion;
    @FXML public PasswordField contrasena;
    @FXML public TextField fechaNacimiento;

    public void Volver(ActionEvent event) {
        Scenes scenes = new Scenes();
        scenes.goMenu(event);
    }

    public void ValidarRegistro(ActionEvent event) {
        try {
            // Crear objeto JSON manualmente (o usar una clase si la tenés)
            String json = new Gson().toJson(new ClientRegisterRequestDto(
                    nombre.getText(),
                    apellidos.getText(),
                    dni.getText(),
                    email.getText(),
                    contrasena.getText(),
                    direccion.getText(),
                    Integer.parseInt(telefono.getText()),
                    fechaNacimiento.getText() // Si es String en el DTO
            ));

            URL url = new URL("http://localhost:8080/gearBorn/api/cliente/registrarCliente");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int status = con.getResponseCode();
            if (status == 201 || status == 200) {
                System.out.println("✔ Cliente registrado correctamente");

                Scenes escena = new Scenes();
                escena.goMenu(event);
            } else if (status == 400) {
                System.out.println("⚠ Error en la solicitud (400): Datos inválidos");
            } else {
                System.out.println("❌ Error inesperado: Código " + status);
            }

            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
