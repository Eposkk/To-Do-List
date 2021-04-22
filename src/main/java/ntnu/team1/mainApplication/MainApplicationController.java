package ntnu.team1.mainApplication;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import ntnu.team1.application.task.Category;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Class that handles the main view of the application
 */

public class MainApplicationController {

    public MenuItem menuEditAdd;
    @FXML
    private MenuItem menuHelpAbout;

    @FXML
    BorderPane view;

    @FXML
    private VBox categoryButtonList;


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
        App.setChosenCategory(id);
        Pane newLoadedPane = FXMLLoader.load(MainApplicationController.class.getResource("task/showByCategory.fxml"));
        view.setCenter(newLoadedPane);
    }

    public void initialize() throws IOException {
        view.setOnMouseMoved(e-> generateCategoryList());
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("task/taskList.fxml"));
        view.setCenter(newLoadedPane);
        menuHelpAbout.setOnAction(showAbout());
        generateCategoryList();
    }

    @FXML
    public void switchToTasks() throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("task/taskList.fxml"));
        view.setCenter(newLoadedPane);
    }

    @FXML
    public void switchToCategory() throws IOException {
        Pane categoryPane = FXMLLoader.load(getClass().getResource("category/categoryList.fxml"));
        view.setCenter(categoryPane);
    }

    @FXML
    private void addNewTask(){
        RegisterModifiers.addNewTask();
    }

    @FXML
    public void addNewCategory() {
        RegisterModifiers.addNewCategory();
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
