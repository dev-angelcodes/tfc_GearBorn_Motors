module com.gearbornmotors.front.gearbornmotorsfront {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;
    requires java.desktop;

    // Apertura para FXML
    opens com.gearbornmotors.front.gearbornmotorsfront to javafx.fxml;
    opens com.gearbornmotors.front.gearbornmotorsfront.Controller to javafx.fxml;

    // Apertura de DTOs a Gson y Jackson
    opens com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente to com.google.gson, com.fasterxml.jackson.databind;
    opens com.gearbornmotors.front.gearbornmotorsfront.Dto.Empleado to com.google.gson, com.fasterxml.jackson.databind;
    opens com.gearbornmotors.front.gearbornmotorsfront.Dto.Vehiculo to com.google.gson, com.fasterxml.jackson.databind;
    opens com.gearbornmotors.front.gearbornmotorsfront.Dto.Gastos to com.google.gson, com.fasterxml.jackson.databind;

    // Exportación de paquetes
    exports com.gearbornmotors.front.gearbornmotorsfront;
    exports com.gearbornmotors.front.gearbornmotorsfront.Controller;
    exports com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente;
    exports com.gearbornmotors.front.gearbornmotorsfront.Dto.Empleado;
    exports com.gearbornmotors.front.gearbornmotorsfront.Dto.Vehiculo;
}
