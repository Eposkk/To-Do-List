package ntnu.team1.mainApplication.task;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.util.Callback;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.mainApplication.App;
import ntnu.team1.mainApplication.MainApplicationController;
import ntnu.team1.mainApplication.RegisterModifiers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class used for displaying the tasks througfh a tableView
 */

public class TaskListController {

    public AnchorPane Pane;

    @FXML
    private Button addNewTool;

    @FXML
    private Button editTool;

    @FXML
    private javafx.scene.control.TableView<MainTask> tableView;

    @FXML
    private TableColumn<MainTask, Boolean> doneColumn;

    @FXML
    private TableColumn<MainTask, String> nameColumn;

    @FXML
    private TableColumn<MainTask, String> descriptionColumn;

    @FXML
    private TableColumn<MainTask, LocalDate> startDateColumn;

    @FXML
    private TableColumn<MainTask, LocalDate> endDateColumn;

    @FXML
    private TableColumn<MainTask, Integer> priorityColumn;

    @FXML
    private TableColumn<MainTask, String> categoryColumn;

    @FXML
    private TableColumn<MainTask, MainTask> infoButtonColumn;

    @FXML
    private TableColumn<MainTask, MainTask> deleteButtonColumn;

    @FXML
    private ToggleGroup choice;

    @FXML
    private Label header;

    /**
     * Initalize method that is run when the class is loaded.
     * Creates the table view and updates it.
     * Also creates buttons that are needed
     * @throws FileNotFoundException Throws if file is not found
     */

    public void initialize() throws FileNotFoundException {
        choice.selectedToggleProperty().addListener((observableValue, toggle, t1) -> updateList());
        header.setText("Viewing all tasks");
        columFactory();
        updateList();
        makeButtons();
        tableView.getColumns().forEach(this::addTooltipToColumnCells);
    }

    private <T> void addTooltipToColumnCells(TableColumn<MainTask, T> column) {

        Callback<TableColumn<MainTask, T>, TableCell<MainTask, T>> existingCellFactory
                = column.getCellFactory();

        column.setCellFactory(c -> {
            TableCell<MainTask, T> cell = existingCellFactory.call(c);

            Tooltip tooltip = new Tooltip();
            // can use arbitrary binding here to make text depend on cell
            // in any way you need:
            if (!cell.itemProperty().asString().equals("")) {
                tooltip.textProperty().bind(cell.itemProperty().asString());
            }
            if(!tooltip.getText().equals("null")){
                cell.setTooltip(tooltip);
            }

            return cell ;
        });
    }

    /**
     * Creates the buttons that are needed
     * @throws FileNotFoundException if path doesnt lead to a file
     */

    private void makeButtons() {
        addNewTool.setTooltip(new Tooltip("Add new task"));

        editTool.setTooltip(new Tooltip(("Edit task")));
    }


    /**
     * Method for adding a new task
     */

    @FXML
    private void addNewTask(){
        RegisterModifiers.addNewTask();
        updateList();
    }

    /**
     * Method for editing a task
     */

    @FXML
    private void editTask(){
        RegisterModifiers.editTask(tableView.getSelectionModel().getSelectedItem());
        updateList();
    }

    /**
     * Factory for creating the tableView
     */

    private void columFactory(){
        doneColumn.setCellFactory(column -> new CheckBoxTableCell<>(){
        });
        doneColumn.setCellValueFactory(cellData -> {
            MainTask task = cellData.getValue();
            BooleanProperty property = new SimpleBooleanProperty(task.isDone());

            property.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->{
                MainRegister register = App.getRegister();
                register.getMainTask(task.getID()).setDone(newValue);
                App.setRegister(register);
                updateList();
            });
            return property;
        });

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        categoryColumn.setCellValueFactory(cellData -> {
            if(cellData.getValue().getCategoryId() < 0){
                return new ReadOnlyStringWrapper("No category");
            }
            else{
                return new ReadOnlyStringWrapper(App.getRegister().getCategory(cellData.getValue().getCategoryId()).getName());
            }
        });
        infoButtonColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        infoButtonColumn.setCellFactory(param -> new TableCell<>() {
            private final Button infoButton = new Button("i");

            @Override
            protected void updateItem(MainTask task, boolean empty) {
                super.updateItem(task, empty);

                if (task == null) {
                    setGraphic(null);
                    return;
                }
                infoButton.setTooltip(new Tooltip("Info/Delete"));
                setGraphic(infoButton);
                infoButton.setOnAction(
                        event -> {
                            RegisterModifiers.editTask(task);
                            updateList();
                        }
                );
            }
        });


        deleteButtonColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        deleteButtonColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button();

            @Override
            protected void updateItem(MainTask task, boolean empty) {
                super.updateItem(task, empty);

                if (task == null) {
                    setGraphic(null);
                    return;
                }
                try {
                    staticMethods.addImageToButton("src/main/resources/Images/deleteAll.png", deleteButton, 20, 20);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                deleteButton.setTooltip(new Tooltip("Delete"));

                setGraphic(deleteButton);
                deleteButton.setOnAction(
                        event -> {
                            RegisterModifiers.removeTask(task);
                            updateList();
                        }
                );
            }
        });
    }

    /**
     * Method for updating the list
     */

    private void updateList(){
        RadioButton r = (RadioButton) choice.getSelectedToggle();
        String selected = r.getText();
        ObservableList<MainTask> list = null;
        switch (selected) {
            case "all":
                list = FXCollections.observableList(new ArrayList<>(App.getRegister().getAllTasks()));
                break;
            case "done":
                list = FXCollections.observableList(App.getRegister().getAllTasks().stream()
                        .filter(MainTask::isDone)
                        .collect(Collectors.toList()));
                break;
            case "active":
                list = FXCollections.observableList(App.getRegister().getAllTasks().stream()
                        .filter(MainTask -> !MainTask.isDone())
                        .collect(Collectors.toList()));
                break;
        }
        tableView.setItems(list);
    }
}
