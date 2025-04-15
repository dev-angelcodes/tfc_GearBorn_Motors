package gearbornmotors.frontgearborn;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ControllerPruebas {

    @FXML
    private TextArea resultadoArea;


    @FXML
    protected void cargaVehiculos() {
        try {
            List<VehiculoDto> vehiculos = obtenerVehiculos();
            StringBuilder sb = new StringBuilder();
            for (VehiculoDto v : vehiculos) {
                sb.append(v.getMarca()).append(" ").append(v.getModelo()).append(" (" + v.getMatricula() + ")\n");
            }
            resultadoArea.setText(sb.toString());
        } catch (Exception e) {
            resultadoArea.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<VehiculoDto> obtenerVehiculos() throws Exception {
        URL url = new URL("http://localhost:8080/gearBorn/api/dealer/vehiculos");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Error HTTP: " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            json.append(line);
        }
        conn.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(json.toString(), VehiculoDto[].class));
    }
}
