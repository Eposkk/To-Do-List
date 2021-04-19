module ntnu.team1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ntnu.team1.mainApplication to javafx.fxml, javafx.base, javafx.graphics;
    opens ntnu.team1.application.task to javafx.fxml, javafx.base, javafx.graphics;
    opens ntnu.team1.mainApplication.category to javafx.base, javafx.fxml, javafx.graphics;
    opens ntnu.team1.mainApplication.task to javafx.base, javafx.fxml, javafx.graphics;

    exports ntnu.team1.mainApplication;
    exports ntnu.team1.mainApplication.category;
    exports ntnu.team1.mainApplication.task;
    exports ntnu.team1.application.task;
    exports ntnu.team1.application;

}