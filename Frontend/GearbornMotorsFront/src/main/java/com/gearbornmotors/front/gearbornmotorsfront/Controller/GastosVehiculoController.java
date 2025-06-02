package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.Gastos.GastoDto;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GastosVehiculoController {

    @FXML
    private VBox gastoPane;

    public void cargarGastos(String matricula) {
        String url = "http://localhost:8080/gearBorn/api/gasto/getGastosByMatricula/" + matricula;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(json -> {
                    Gson gson = new Gson();
                    GastoDto[] gastos = gson.fromJson(json, GastoDto[].class);

                    Platform.runLater(() -> {
                        gastoPane.getChildren().clear();
                        for (GastoDto gasto : gastos) {
                            Label gastoLabel = new Label(
                                    "📅 " + gasto.getFecha() +
                                            " | 💶 " + gasto.getImporte() +
                                            " | 🏢 " + gasto.getNombreProv() +
                                            "\n📝 " + gasto.getDescripcion()
                            );
                            gastoLabel.setStyle("-fx-background-color: #eeeeee; -fx-padding: 10; -fx-border-color: #ccc;");
                            gastoPane.getChildren().add(gastoLabel);
                        }
                    });
                })
                .exceptionally(e -> {
                    Platform.runLater(() -> {
                        gastoPane.getChildren().add(new Label("⚠ Error al cargar los gastos: " + e.getMessage()));
                    });
                    return null;
                });
    }
}
