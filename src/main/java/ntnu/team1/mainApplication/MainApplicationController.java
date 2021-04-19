package ntnu.team1.mainApplication;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import ntnu.team1.mainApplication.task.EditTaskDialogController;

import java.io.IOException;

public class MainApplicationController {

    @FXML
    private MenuItem menuHelpAbout;

    @FXML
    private AnchorPane view;

    @FXML
    private VBox categoryButtonList;


    private void generateCategoryList(){
        categoryButtonList.getChildren().clear();
        for(Category c : App.getCategoryWrapper()){
            Button button = new Button(c.getName());
            button.setId(c.getName());
            button.setOnAction(actionEvent ->  {
                try {
                    showByCategory(c.getID());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            categoryButtonList.getChildren().add(button);
        }
    }

    public void showByCategory(int id) throws IOException {
        view.getChildren().clear();
        App.setChosenCategory(id);
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("task/showByCategory.fxml"));
        view.getChildren().add(newLoadedPane);
    }
    private String currentView="todo";


    public void initialize() throws IOException {
        view.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("task/taskList.fxml"));

        view.getChildren().add(newLoadedPane);
        menuHelpAbout.setOnAction(showAbout());
        generateCategoryList();
    }

    @FXML
    public void switchToFinished() throws IOException {
        App.changeTaskWrapper(true);
        initialize();
    }
    @FXML
    public void switchToToDo() throws IOException {
        App.changeTaskWrapper(false);
        currentView="todo";
        initialize();
    }

    @FXML
    public void switchToCategory() throws IOException {
        view.getChildren().clear();
        System.out.println("Test");
        Pane categoryPane = FXMLLoader.load(getClass().getResource("category/categoryList.fxml"));
        currentView="category";
        view.getChildren().add(categoryPane);
    }

    @FXML
    private void addNewTask() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "task/newTask.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 800, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    public void addNewCategory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "category/newCategory.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 400,364);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    public void edit(){
    }


    @FXML
    private EventHandler<ActionEvent> showAbout() {
        return event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("About");
            alert.setHeaderText("To-Do-List Application");
            alert.setContentText("Version 1.0\n" +
                    "Made by Team 1.1" +
                    "\n2021 \u00A9");
            alert.showAndWait();
        };
    }

    @FXML
    public void close(){
        Platform.exit();
    }
}
