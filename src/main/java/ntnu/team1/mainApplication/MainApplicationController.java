package ntnu.team1.mainApplication;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
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

    @FXML
    public MenuItem menuEditAdd;
    public AnchorPane pane;
    @FXML
    private MenuItem menuHelpAbout;
    @FXML
    BorderPane view;
    @FXML
    private VBox categoryButtonList;

    /**
     * Method that gets called on load of class
     * Sets up necessary layout and configures it
     * @throws IOException if the fxml file isnt found
     */

    public void initialize() throws IOException {
        pane.setOnMouseMoved(e-> generateCategoryList());
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("task/taskList.fxml"));
        view.setCenter(newLoadedPane);
        menuHelpAbout.setOnAction(showAbout());
        generateCategoryList();
    }

    /**
     * Method for generating the category list and displaying it
     * Configures the view and adds neccesary information
     */

    public void generateCategoryList() {
        ObservableList<Category> list = FXCollections.observableList(new ArrayList<>(App.getRegister().getCategories().values()));
        categoryButtonList.getChildren().clear();

        categoryButtonList.setSpacing(5);
        for (Category c : list) {
            Circle colorCircle = new Circle(5, c.getColor());
            Button button = new Button(c.getName(), colorCircle);
            button.setGraphicTextGap(10);
            button.setPrefWidth(categoryButtonList.getWidth());
            button.getStyleClass().add("sideMenuCategoryButton");
            button.setOnMousePressed(actionEvent -> {
                try {
                    showByCategory(c.getID());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            categoryButtonList.getChildren().add(button);
        }
    }

    /**
     * Changes the pane to category view
     * @param id Id of the category we want to load
     * @throws IOException If the fxml file cant be found
     */

    public void showByCategory(int id) throws IOException {
        App.setChosenCategory(id);
        Pane newLoadedPane = FXMLLoader.load(MainApplicationController.class.getResource("task/showByCategory.fxml"));
        view.setCenter(newLoadedPane);
    }

    /**
     * Switches the view to tasks
     * @throws IOException Throws IOException if the fxml file cannot be found
     */
    @FXML
    public void switchToTasks() throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("task/taskList.fxml"));
        App.setChosenCategory(-1);
        view.setCenter(newLoadedPane);
    }

    /**
     * Switches the view to categories
     * @throws IOException Throws IOException if the fxml file cannot be found
     */
    @FXML
    public void switchToCategory() throws IOException {
        Pane categoryPane = FXMLLoader.load(getClass().getResource("category/categoryList.fxml"));
        view.setCenter(categoryPane);
    }

    /**
     * Adds new task
     */

    @FXML
    private void addNewTask(){
        RegisterModifiers.addNewTask();
    }

    /**
     * Adds new category
     */

    @FXML
    public void addNewCategory() {
        RegisterModifiers.addNewCategory();
    }

    /**
     * Makes about page display if the button is clicked on
     * @return An eventHandler
     */
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


    /**
     * Gets called on exit.
     */

    @FXML
    public void exit() {
        App.alertOnExit();
    }
}
