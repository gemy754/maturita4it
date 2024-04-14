module com.example.vypocetmzdy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vypocetmzdy to javafx.fxml;
    exports com.example.vypocetmzdy;
}