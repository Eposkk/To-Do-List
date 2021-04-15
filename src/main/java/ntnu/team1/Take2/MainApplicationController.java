package ntnu.team1.Take2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplicationController {



    @FXML
    private AnchorPane view;

    public void initialize() throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("toDo.fxml"));
        view.getChildren().add(newLoadedPane);
    }

    @FXML
    public void switchToFinished() throws IOException {
        view.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(App.class.getResource("finished.fxml"));
        view.getChildren().add(newLoadedPane);
    }
    @FXML
    public void switchToToDo() throws IOException {
        view.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(App.class.getResource("toDo.fxml"));
        view.getChildren().add(newLoadedPane);
    }

    @FXML
    private void addNewTask() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "newtask.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 800, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
