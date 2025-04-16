module com.gearbornmotors.front.gearbornmotorsfront {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;


    opens com.gearbornmotors.front.gearbornmotorsfront to javafx.fxml;
    exports com.gearbornmotors.front.gearbornmotorsfront;
    exports com.gearbornmotors.front.gearbornmotorsfront.Controller;
    opens com.gearbornmotors.front.gearbornmotorsfront.Controller to javafx.fxml;
}