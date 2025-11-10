module AMS_JavaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens ams to javafx.fxml;
    opens ams.controller to javafx.fxml;
    exports ams;
    exports ams.controller;
    exports ams.model;
    exports ams.util;
}
