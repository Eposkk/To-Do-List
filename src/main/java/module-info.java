module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens cardgame to javafx.fxml;
    exports cardgame;
}