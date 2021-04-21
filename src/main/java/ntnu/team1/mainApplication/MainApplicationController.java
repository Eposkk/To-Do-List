package ntnu.team1.mainApplication;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ntnu.team1.application.task.Category;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import ntnu.team1.application.task.Category;

/**
 * Class that handles the main view of the application
 */

public class MainApplicationController {

    public MenuItem menuEditAdd;
    @FXML
    private MenuItem menuHelpAbout;

    @FXML
    private AnchorPane view;

    @FXML
    private VBox categoryButtonList;

    private String currentView = "todo";


    public void generateCategoryList() {
        ObservableList<Category> list = FXCollections.observableList(new ArrayList<>(App.getRegister().getCategories().values()));
        categoryButtonList.getChildren().clear();
        if (App.getRegister().getAllTasks().stream().anyMatch(MainTask -> !MainTask.hasCategory())) {
            Button noCategory = new Button("No category");
            noCategory.setOnAction(actionEvent -> {
                try {
                    showByCategory(-1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            categoryButtonList.getChildren().add(noCategory);
        }

        for (Category c : list) {
            Button button = new Button(c.getName());
            button.setId(c.getName());
            button.setOnAction(actionEvent -> {
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
        Pane newLoadedPane = FXMLLoader.load(MainApplicationController.class.getResource("task/showByCategory.fxml"));
        view.getChildren().add(newLoadedPane);
    }

    public void initialize() throws IOException {
        view.setOnMouseMoved(e-> generateCategoryList());
        view.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("task/taskList.fxml"));
        view.getChildren().add(newLoadedPane);
        menuHelpAbout.setOnAction(showAbout());
        generateCategoryList();
    }

    @FXML
    public void switchToTasks() throws IOException {
        view.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("task/taskList.fxml"));
        view.getChildren().add(newLoadedPane);
    }

    @FXML
    public void switchToCategory() throws IOException {
        view.getChildren().clear();
        Pane categoryPane = FXMLLoader.load(getClass().getResource("category/categoryList.fxml"));
        currentView = "category";
        view.getChildren().add(categoryPane);
    }

    @FXML
    private void addNewTask() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("task/newTask.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 800, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Add new task");
        stage.getIcons().add(new Image(new FileInputStream("src/main/resources/Images/Plus.png")));
        stage.showAndWait();
    }

    @FXML
    public void addNewCategory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("category/newCategory.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 400, 364);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        generateCategoryList();
    }

    @FXML
    public void edit() {
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
    public static ArrayList<Button> displayToolbarIcons( Button addNewTool , Button deleteTool, Button editTool,Button deleteAllTool) throws FileNotFoundException {
        ArrayList<Button> buttons = new ArrayList<>();
        FileInputStream inputAdd = new FileInputStream("src/main/resources/Images/Plus.png");
        Image imageAdd = new Image(inputAdd);
        ImageView addTaskIcon = new ImageView(imageAdd);
        addTaskIcon.setFitWidth(30);
        addTaskIcon.setFitHeight(30);
        addNewTool.setGraphic(addTaskIcon);
        System.out.println(addNewTool);
        Tooltip tooltipAdd = new Tooltip("Add new task");
        addNewTool.setTooltip(tooltipAdd);
        buttons.add(addNewTool);

        FileInputStream inputRemove = new FileInputStream("src/main/resources/Images/DeleteTaskIcon.png");
        Image imageRemove = new Image(inputRemove);
        ImageView removeTaskIcon = new ImageView(imageRemove);
        removeTaskIcon.setFitWidth(30);
        removeTaskIcon.setFitHeight(30);
        deleteTool.setGraphic(removeTaskIcon);
        Tooltip tooltipRemove = new Tooltip("Remove task");
        deleteTool.setTooltip(tooltipRemove);
        buttons.add(deleteTool);


        FileInputStream inputEdit = new FileInputStream("src/main/resources/Images/editIcons.png");
        Image imageEdit = new Image(inputEdit);
        ImageView editTaskIcon = new ImageView(imageEdit);
        editTaskIcon.setFitWidth(30);
        editTaskIcon.setFitHeight(30);
        editTool.setGraphic(editTaskIcon);
        Tooltip tooltipEdit = new Tooltip("Edit task");
        editTool.setTooltip(tooltipEdit);
        buttons.add(editTool);

        FileInputStream inputDeleteAll = new FileInputStream("src/main/resources/Images/søppelkasse.png");
        Image imageDeleteAll = new Image(inputDeleteAll);
        ImageView deleteAllIcon = new ImageView(imageDeleteAll);
        deleteAllIcon.setFitWidth(30);
        deleteAllIcon.setFitHeight(30);
        deleteAllTool.setGraphic(deleteAllIcon);
        Tooltip tooltipRemoveAll = new Tooltip("Remove all tasks");
        deleteAllTool.setTooltip(tooltipRemoveAll);
        buttons.add(deleteAllTool);

        return buttons;
    }

    @FXML
    public void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Do you want to exit the application?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}
