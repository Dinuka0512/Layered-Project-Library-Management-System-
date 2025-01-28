module edu.ijse.gdse.libarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires net.sf.jasperreports.core;
    requires java.mail;

    opens edu.ijse.gdse.libarymanagementsystem.dto to javafx.base;
    opens edu.ijse.gdse.libarymanagementsystem.dto.tm to javafx.base;
    opens edu.ijse.gdse.libarymanagementsystem.controller to javafx.fxml;
    exports edu.ijse.gdse.libarymanagementsystem;
}
