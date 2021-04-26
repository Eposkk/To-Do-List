package ntnu.team1.mainApplication.task;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.mainApplication.App;
import ntnu.team1.mainApplication.RegisterModifiers;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class used for displaying the tasks througfh a tableView
 */

public class TaskListController {

    /**
     * Pane used as the upper element
     */
    public AnchorPane Pane;

    /**
     * Button that launches the add new task dialog
     */
    @FXML
    private Button addNewTool;

    /**
     * Button that launches the edit task dialog
     */
    @FXML
    private Button editTool;
    /**
     * Table view used for showing all tasks
     */

    @FXML
    private javafx.scene.control.TableView<MainTask> tableView;

    /**
     * Column where you can mark tasks as done
     */

    @FXML
    private TableColumn<MainTask, Boolean> doneColumn;

    /**
     * Column for the task name
     */

    @FXML
    private TableColumn<MainTask, String> nameColumn;

    /**
     * Column for the task description
     */
    @FXML
    private TableColumn<MainTask, String> descriptionColumn;
    /**
     * Column for the task startDate
     */
    @FXML
    private TableColumn<MainTask, LocalDate> startDateColumn;
    /**
     * Column for the task endDate
     */
    @FXML
    private TableColumn<MainTask, LocalDate> endDateColumn;
    /**
     * Column for the task priority
     */
    @FXML
    private TableColumn<MainTask, Integer> priorityColumn;

    /**
     * Column for the task category
     */
    @FXML
    private TableColumn<MainTask, MainTask> categoryColumn;

    /**
     * Column for info button for each task
     */
    @FXML
    private TableColumn<MainTask, MainTask> infoButtonColumn;

    /**
     * Column for the delete button
     */
    @FXML
    private TableColumn<MainTask, MainTask> deleteButtonColumn;

    /**
     * Toggle group for which tasks you want to show
     */
    @FXML
    private ToggleGroup choice;

    /**
     * Header to indicate which
     */
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
        categoryColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        categoryColumn.setCellFactory(param -> new TableCell<>() {
            private final GridPane box = new GridPane();
            @Override
            protected void updateItem(MainTask task, boolean empty) {
                box.getChildren().clear();
                super.updateItem(task, empty);
                if (task == null) {
                    setGraphic(null);
                    return;
                }
                Circle colorCircle = new Circle();
                colorCircle.setFill(App.getRegister().getCategory(task.getCategoryId()).getColor());
                colorCircle.setRadius(5);

                Label categoryName = new Label(App.getRegister().getCategory(task.getCategoryId()).getName());

                box.add(colorCircle, 1, 1);
                box.add(categoryName, 2,1);
                box.setHgap(5);
                setGraphic(box);
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
                infoButton.setTooltip(new Tooltip("Info/Edit"));
                setGraphic(infoButton);
                infoButton.setOnAction(event -> RegisterModifiers.editTask(task));
            }
        });


        deleteButtonColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        deleteButtonColumn.setCellFactory(param -> new TableCell<>() {
            private Button deleteButton = new Button();

            @Override
            protected void updateItem(MainTask task, boolean empty) {
                deleteButton = new Button();
                super.updateItem(task, empty);

                if (task == null) {
                    setGraphic(null);
                    return;
                }
                try {
                    StaticMethods.addImageToButton("src/main/resources/Images/deleteAll.png", deleteButton, 20, 20);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                deleteButton.setTooltip(new Tooltip("Delete"));

                setGraphic(deleteButton);
                deleteButton.setOnAction(
                        event -> {
                            try {
                                RegisterModifiers.removeTask(task);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
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
