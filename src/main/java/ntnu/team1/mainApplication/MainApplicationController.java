package ntnu.team1.mainApplication;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(0, 5, 5, 5));

        gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
        int i = 0;
        for (Category c : list) {
            i++;
            Label button = new Label(c.getName());
            button.setId(c.getName());
            button.prefWidthProperty().bind(gridPane.widthProperty());
            button.setCursor(Cursor.HAND);
            button.setOnMousePressed(actionEvent -> {
                try {
                    showByCategory(c.getID());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Circle colorCircle = new Circle(5, c.getColor());
            gridPane.add(button, 1, 1+i);
            gridPane.add(colorCircle, 2,1+i);
        }
        categoryButtonList.getChildren().add(gridPane);
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
