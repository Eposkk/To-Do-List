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

import ntnu.team1.application.exceptions.RemoveException;
import ntnu.team1.application.task.Category;
import ntnu.team1.mainApplication.App;
import ntnu.team1.mainApplication.RegisterModifiers;

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
    public TableColumn<Color, Color> colorColumn;
    @FXML
    public TableColumn<Category, Integer> taskNumberColumn;
    @FXML
    public TableColumn<Category, Category> deleteButtonColumn;

    public void initialize() throws FileNotFoundException {
        columFactory();
        updateList();

        tableView.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && (mouseEvent.getClickCount() == 2)) {
                App.setChosenCategory(tableView.getSelectionModel().getSelectedItem().getID());

            }
        });
        makeButtons();
    }

    private void makeButtons() throws FileNotFoundException {
        addImageToButton("src/main/resources/Images/addNew.png", addNewTool);
        addNewTool.setTooltip(new Tooltip("Add new task"));

        addImageToButton("src/main/resources/Images/edit.png", editTool);
        editTool.setTooltip(new Tooltip(("Edit task")));
    }

    private void addImageToButton(String path, Button button) throws FileNotFoundException {
        FileInputStream inputAdd = new FileInputStream(path);
        Image imageAdd = new Image(inputAdd);
        ImageView addPatientIcon = new ImageView(imageAdd);
        addPatientIcon.setFitWidth(30);
        addPatientIcon.setFitHeight(30);
        button.setGraphic(addPatientIcon);
    }

    private void columFactory(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        deleteButtonColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        deleteButtonColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (category == null) {
                    setGraphic(null);
                    return;
                }
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
        });
        taskNumberColumn.setCellValueFactory(cellData -> {
            int number =  App.getRegister().getAllTasks().stream()
                    .filter(MainTask -> MainTask.getCategoryId() == cellData.getValue().getID())
                    .collect(Collectors.toList()).size();
            return  new ReadOnlyObjectWrapper<>(number);

        });

    }


    @FXML
    public void addNewCategory(){
        RegisterModifiers.addNewCategory();
        updateList();
    }

    @FXML
    public void editCategory(){
        RegisterModifiers.editCategory(tableView.getSelectionModel().getSelectedItem());
       updateList();
    }

    private void updateList(){
        ObservableList<Category> list = FXCollections.observableList(new ArrayList<>(App.getRegister().getCategories().values()));
        tableView.setItems(list);
    }
}
