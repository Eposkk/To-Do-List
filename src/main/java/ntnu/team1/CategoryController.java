package ntnu.team1;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import ntnu.team1.application.MainRegister;

import java.io.IOException;



public class CategoryController {
    MainRegister register = new MainRegister();

    @FXML
    private void switchToUpcoming() throws IOException {
        App.setRoot("upcoming");
    }

    @FXML
    private void switchToFinished() throws IOException {
        App.setRootWithSave("finished", register);
    }
}
