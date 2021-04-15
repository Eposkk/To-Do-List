package ntnu.team1.Take2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplicationController {

    @FXML
    private MenuItem menuFileClose;

    @FXML
    private MenuItem menuEditAdd;

    @FXML
    private MenuItem menuEditEdit;

    @FXML
    private MenuItem menuEditDelete;

    @FXML
    private MenuItem menuHelpAbout;

    @FXML
    private Button add;

    @FXML
    private AnchorPane view;


    public void initialize() throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("toDo.fxml"));
        view.getChildren().add(newLoadedPane);
    }

    @FXML
    private void addNewTask() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "newtask.fxml"));
        Parent parent = fxmlLoader.load();
        AddTaskDialogController dialogController = new AddTaskDialogController();
        ToDoController toDoController = new ToDoController();
        dialogController.setAppMainObservableList(toDoController.getRegisterWrapper());

        Scene scene = new Scene(parent, 800, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
