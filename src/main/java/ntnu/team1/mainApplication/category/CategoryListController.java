package ntnu.team1.mainApplication.category;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;
import ntnu.team1.application.exceptions.RemoveException;
import ntnu.team1.application.task.Category;
import ntnu.team1.mainApplication.App;
import ntnu.team1.mainApplication.RegisterModifiers;
import ntnu.team1.mainApplication.task.staticMethods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class that is used for displaying the categories
 */

public class CategoryListController {

    @FXML
    private Button addNewTool;
    @FXML
    private Button editTool;
    @FXML
    private TableView<Category> tableView;
    @FXML
    public TableColumn<Category, String> nameColumn;
    @FXML
    public TableColumn<Category, Category> colorColumn;
    @FXML
    public TableColumn<Category, Integer> taskNumberColumn;
    @FXML
    public TableColumn<Category, Category> deleteButtonColumn;

    /**
     * Initalize method that is run when the class is loaded.
     * Creates the table view and updates it.
     * Also creates buttons that are needed
     * @throws FileNotFoundException Throws if file is not found
     */

    public void initialize() {
        columFactory();
        updateList();

        tableView.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && (mouseEvent.getClickCount() == 2)) {
                App.setChosenCategory(tableView.getSelectionModel().getSelectedItem().getID());

            }
        });
        makeButtons();
    }

    /**
     * Loads pictures used for creating buttons and sets tooltips
     * @throws FileNotFoundException Throws if file is not found
     */

    private void makeButtons() {
        addNewTool.setTooltip(new Tooltip("Add new category"));
        editTool.setTooltip(new Tooltip(("Edit category")));
    }

    /**
     * Adds the images to buttons
     * @param path Path of the images
     * @param button The button to add the images to
     * @throws FileNotFoundException Throws if file is not found
     */



    /**
     * Factory for creating the tableview and adding information
     */

    private void columFactory(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        colorColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        colorColumn.setCellFactory(param -> new TableCell<>() {
            private final Circle colorCircle = new Circle();
            @Override
            protected void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (category == null) {
                    setGraphic(null);
                    return;
                }
                colorCircle.setFill(category.getColor());
                colorCircle.setRadius(10);
                setGraphic(colorCircle);

            }
        });


        deleteButtonColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        deleteButtonColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("");

            @Override
            protected void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (category == null) {
                    setGraphic(null);
                    return;
                }
                if(category.getID()>-1){
                    try {
                        staticMethods.addImageToButton("src/main/resources/Images/deleteAll.png", deleteButton, 20, 20);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    deleteButton.setTooltip(new Tooltip("Delete"));
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(
                            event -> {
                                try {
                                    RegisterModifiers.removeCategory(category);
                                } catch (RemoveException e) {
                                    e.printStackTrace();
                                }
                                updateList();
                            }
                    );
                }

            }
        });


        taskNumberColumn.setCellValueFactory(cellData -> {
            int number =  App.getRegister().getAllTasks().stream()
                    .filter(MainTask -> MainTask.getCategoryId() == cellData.getValue().getID())
                    .collect(Collectors.toList()).size();
            return  new ReadOnlyObjectWrapper<>(number);

        });

    }

    /**
     * Method called for adding new category
     */

    @FXML
    public void addNewCategory(){
        RegisterModifiers.addNewCategory();
        updateList();
    }
    /**
     * Method called for editing new category
     */

    @FXML
    public void editCategory(){
        RegisterModifiers.editCategory(tableView.getSelectionModel().getSelectedItem());
       updateList();
    }

    /**
     * Method for updating the list used by tableview
     */

    private void updateList(){
        ObservableList<Category> list = FXCollections.observableList(new ArrayList<>(App.getRegister().getCategories().values()));
        tableView.setItems(list);
    }
}
