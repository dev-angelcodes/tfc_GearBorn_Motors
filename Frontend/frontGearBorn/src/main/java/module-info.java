module gearbornmotors.frontgearborn {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens gearbornmotors.frontgearborn to javafx.fxml;
    exports gearbornmotors.frontgearborn;
}