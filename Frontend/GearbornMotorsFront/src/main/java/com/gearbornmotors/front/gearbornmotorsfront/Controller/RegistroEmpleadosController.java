package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.Empleado.EmpleadoRegisterRequestDto;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class RegistroEmpleadosController {

    @FXML public MenuButton tipoEmpleado;
    @FXML public MenuItem administrador;
    @FXML public MenuItem comercial;
    @FXML public TextField dni;
    @FXML public TextField correo;
    @FXML public TextField fechaNacimiento;
    @FXML public TextField nombre;
    @FXML public TextField apellidos;
    @FXML public TextField numeroTelefono;
    @FXML public TextField fechaCreacionContrato;
    @FXML public PasswordField contrasenha;


    public void initialize(){
        administrador.setOnAction(event -> {
            tipoEmpleado.setText("Administrador");
        });

        comercial.setOnAction(event -> {
            tipoEmpleado.setText("Comercial de ventas");
        });
    }

    @FXML
    public void Registro(ActionEvent event) {
        Optional<EmpleadoRegisterRequestDto> empleado =ValidarCampos();

        if (empleado.isPresent()) {
            try {
                // Crear cliente HTTP
                HttpClient client = HttpClient.newHttpClient();

                // Convertir objeto a JSON
                Gson gson = new Gson();
                String jsonEmpleado = gson.toJson(empleado.get());

                // Crear request HTTP POST
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/gearBorn/api/empleado/registrarEmpleado"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonEmpleado))
                        .build();

                // Enviar request y obtener respuesta
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 201) {
                    System.out.println("Empleado registrado correctamente.");
                } else {
                    System.out.println("Error al registrar empleado: " + response.body());
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("No se pudo conectar con el servidor.");
            }
        }
    }

    private Optional<EmpleadoRegisterRequestDto> ValidarCampos() {
        if(ValidarTipo() && ValidarContrasena() && ValidarDNI() && ValidarEmail() && ValidarNombreApellidos(nombre.getText().trim())
                && ValidarNombreApellidos(apellidos.getText().trim()) && ValidarFecha(fechaNacimiento.getText().trim())
                && ValidarFecha(fechaCreacionContrato.getText().trim()) && ValidarTelefono()) {

            EmpleadoRegisterRequestDto empleado = CreacionEmpleado();

            return Optional.of(empleado);
        } else {
            return Optional.empty();
        }
    }

    private EmpleadoRegisterRequestDto CreacionEmpleado() {
        EmpleadoRegisterRequestDto empleado = new EmpleadoRegisterRequestDto();
        empleado.setContrasena(LoginController.md5(contrasenha.getText().trim()));
        empleado.setTipo(tipoEmpleado.getText().trim());
        empleado.setSuspendido(false);
        empleado.setDni(dni.getText().trim());
        empleado.setEmail(correo.getText().trim());
        empleado.setNombre(nombre.getText().trim());
        empleado.setApellidos(apellidos.getText().trim());
        empleado.setFechaNacimiento(fechaNacimiento.getText().trim());
        empleado.setFechaContrato(fechaCreacionContrato.getText().trim());
        empleado.setNumeroTelefono(Integer.parseInt(numeroTelefono.getText().trim()));
        return empleado;
    }

    private boolean ValidarTelefono() {
        try{
            int numeroTelefonoText = Integer.parseInt(numeroTelefono.getText().trim());
            if(numeroTelefono.getText().length() == 9){
                return true;
            } else {
                System.out.println("El número de teléfono debe tener 9 dígitos.");
                return false;
            }
        }catch (NumberFormatException e){
            System.out.println("El número de teléfono no es válido.");
            return false;
        }
    }

    private boolean ValidarFecha(String fecha) {
        if(fecha.matches("\\d{4}-\\d{2}-\\d{2}")){
            return true;
        } else {
            System.out.println("La fecha de nacimiento no es válida.");
            return false;
        }
    }

    private boolean ValidarNombreApellidos(String nombreApellidos) {
        if(nombreApellidos.length() >= 3 && nombreApellidos.matches("[a-zA-Z]+")){
            return true;
        } else {
            System.out.println("El nombre debe tener al menos 3 caracteres y solo letras.");
            return false;
        }
    }

    private boolean ValidarEmail() {
        String email = correo.getText().trim();
        if(email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")){
            return true;
        } else {
            System.out.println("El email no es válido.");
            return false;
        }
    }

    private boolean ValidarDNI() {
        String dniText = dni.getText().trim();
        if(dniText.length() == 9 && dniText.matches("[0-9]{8}[A-Z]")){
            return true;
        } else {
            System.out.println("El DNI debe tener 8 dígitos y una letra al final.");
            return false;
        }
    }

    private boolean ValidarContrasena() {
        String contrasena = contrasenha.getText().trim();
        if(contrasena.length() >= 8 && contrasena.matches(".*[A-Z].*") && contrasena.matches(".*[0-9].*")){
            return true;
        } else {
            System.out.println("La contraseña debe tener al menos 8 caracteres, una letra mayúscula y un número.");
            return false;
        }
    }

    private boolean ValidarTipo() {
        String tipo = tipoEmpleado.getText().trim();
        if(tipo.equals("Administrador") || tipo.equals("Comercial de ventas")){
            return true;
        } else {
            System.out.println("Por favor, selecciona un tipo de empleado válido.");
            return false;
        }
    }

}
