module ntnu.team1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ntnu.team1 to javafx.fxml;
    exports ntnu.team1;
}