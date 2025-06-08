package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Alerts.Alertas;
import com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente.ClientRegisterRequestDto;
import com.gearbornmotors.front.gearbornmotorsfront.Scenes;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class RegistroClienteController {

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
            // Comprobación de campos vacíos
            if (nombre.getText().isBlank() || apellidos.getText().isBlank() || dni.getText().isBlank()
                    || email.getText().isBlank() || contrasena.getText().isBlank()
                    || direccion.getText().isBlank() || telefono.getText().isBlank()
                    || fechaNacimiento.getText().isBlank()) {

                Alertas.advertencia("Campos obligatorios", "Faltan datos", "Por favor, completa todos los campos antes de continuar.");
                System.out.println("⚠ Faltan campos por completar");
                return;
            }

            // Validación de número de teléfono
            int telefonoNumerico;
            try {
                telefonoNumerico = Integer.parseInt(telefono.getText());
            } catch (NumberFormatException nfe) {
                Alertas.error("Teléfono inválido", "Número no válido", "Introduce un número de teléfono válido (solo dígitos).");
                System.out.println("❌ Error: Teléfono no numérico");
                return;
            }

            // Crear el objeto JSON
            String json = new Gson().toJson(new ClientRegisterRequestDto(
                    nombre.getText(),
                    apellidos.getText(),
                    dni.getText(),
                    email.getText(),
                    contrasena.getText(),
                    direccion.getText(),
                    telefonoNumerico,
                    fechaNacimiento.getText()
            ));

            // Conexión HTTP
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
                Alertas.info("Registro exitoso", "Cliente registrado", "El cliente ha sido registrado correctamente.");
                System.out.println("✔ Cliente registrado correctamente");

                new Scenes().goMenu(event);
            } else if (status == 400) {
                Alertas.error("Error de registro", "Datos inválidos", "Verifica los campos y vuelve a intentarlo.");
                System.out.println("⚠ Error 400: Datos inválidos");
            } else {
                Alertas.error("Error desconocido", "Problema en el servidor", "No se pudo completar el registro. Inténtalo más tarde.");
                System.out.println("❌ Error desconocido. Código HTTP: " + status);
            }

            con.disconnect();
        } catch (Exception e) {
            Alertas.error("Excepción", "Error inesperado", "Ha ocurrido un error durante el registro.");
            System.out.println("❌ Excepción en ValidarRegistro: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
