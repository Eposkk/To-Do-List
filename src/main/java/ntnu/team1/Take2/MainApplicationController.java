package ntnu.team1.Take2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainApplicationController {

    @FXML
    private AnchorPane view;

    public void initialize() throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("toDo.fxml"));
        view.getChildren().add(newLoadedPane);
    }



}
