module ntnu.team1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ntnu.team1.Take2 to javafx.fxml, javafx.base, javafx.graphics;
    opens ntnu.team1.application.task to javafx.fxml, javafx.base, javafx.graphics;
    exports ntnu.team1.Take2;
}