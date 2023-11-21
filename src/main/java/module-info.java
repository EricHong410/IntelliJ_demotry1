module com.example.demotry {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demotry to javafx.fxml;
    exports com.example.demotry;
}