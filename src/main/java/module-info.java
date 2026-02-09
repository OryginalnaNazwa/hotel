module org.example.hotel {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires javafx.graphics;
    requires javafx.base;
    requires org.json;

    opens org.example.hotel to javafx.fxml;
    exports org.example.hotel;
}