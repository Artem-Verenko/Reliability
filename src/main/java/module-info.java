module com.example.reliability {
    requires javafx.controls;
    requires javafx.fxml;
    requires flanagan;


    opens com.example.reliability to javafx.fxml;
    exports com.example.reliability;
}