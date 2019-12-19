module de.nicrizzos {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.nicrizzos to javafx.fxml;
    exports de.nicrizzos;
}