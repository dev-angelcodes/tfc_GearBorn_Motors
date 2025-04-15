module gearbornmotors.frontgearborn {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens gearbornmotors.frontgearborn to javafx.fxml;
    exports gearbornmotors.frontgearborn;
    exports gearbornmotors.frontgearborn.Dto;
    opens gearbornmotors.frontgearborn.Dto to javafx.fxml;
    exports gearbornmotors.frontgearborn.controller;
    opens gearbornmotors.frontgearborn.controller to javafx.fxml;
}