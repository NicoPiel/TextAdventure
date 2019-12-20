module de.nicrizzos {
    requires javafx.controls;
    requires javafx.fxml;
      requires java.sql;
      
      opens de.nicrizzos to javafx.fxml;
    exports de.nicrizzos;
}