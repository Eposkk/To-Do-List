package ntnu.team1.Take2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        view.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("toDo.fxml"));
        view.getChildren().add(newLoadedPane);
        menuHelpAbout.setOnAction(showAbout());
    }

    @FXML
    public void switchToFinished() throws IOException {
        App.changeWrapper(true);
        initialize();
    }
    @FXML
    public void switchToToDo() throws IOException {
        App.changeWrapper(false);
        initialize();
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

    @FXML
    private EventHandler<ActionEvent> showAbout() {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("About");
                alert.setHeaderText("To-Do-List Application");
                alert.setContentText("Version 1.0\n" +
                        "Made by Team 1.1"+
                        "\n2021 \u00A9");
                alert.showAndWait();
            }
        };
        return eventHandler;
    }

}
