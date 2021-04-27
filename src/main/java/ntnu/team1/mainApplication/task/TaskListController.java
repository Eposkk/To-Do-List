package ntnu.team1.mainApplication.task;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
    private TableColumn<MainTask, MainTask> priorityColumn;

    /**
     * Column for the task category
     */
    @FXML
    private TableColumn<MainTask, MainTask> categoryColumn;

    /**
     * Column for the delete button
     */
    @FXML
    private TableColumn<MainTask, MainTask> buttonColumn;

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
     */

    public void initialize(){
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
            @Override
            protected void updateItem(MainTask task, boolean empty) {

                super.updateItem(task, empty);
                if (task == null) {
                    setGraphic(null);
                    return;
                }

                Label container = new Label();
                Circle colorCircle = new Circle();
                colorCircle.setFill(App.getRegister().getCategory(task.getCategoryId()).getColor());
                colorCircle.setRadius(5);

                container.setText(App.getRegister().getCategory(task.getCategoryId()).getName());
                container.setGraphic(colorCircle);
                container.setGraphicTextGap(5);
                setGraphic(container);
            }

        });

        buttonColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        buttonColumn.setCellFactory(param -> new TableCell<>() {

            @Override
            protected void updateItem(MainTask task, boolean empty) {

                super.updateItem(task, empty);

                if (task == null) {
                    setGraphic(null);
                    return;
                }

                Button infoButton = new Button("?");
                infoButton.setTooltip(new Tooltip("Info/Edit"));;
                infoButton.setPrefHeight(30);
                infoButton.setPrefWidth(30);
                infoButton.setOnAction(event -> {
                    RegisterModifiers.editTask(task);
                    updateList();
                });

                Button deleteButton = new Button();
                try {
                    StaticMethods.addImageToButton("src/main/resources/Images/deleteAll.png", deleteButton, 20, 20);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                deleteButton.setTooltip(new Tooltip("Delete"));
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

                HBox container = new HBox();
                container.setAlignment(Pos.CENTER);
                container.getChildren().add(infoButton);
                container.getChildren().add(deleteButton);
                container.setSpacing(10);

                Label label = new Label();

                label.setGraphic(container);
                setGraphic(label);

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
                header.setText("Viewing all tasks");
                break;
            case "done":
                list = FXCollections.observableList(App.getRegister().getAllTasks().stream()
                        .filter(MainTask::isDone)
                        .collect(Collectors.toList()));
                header.setText("Viewing all done tasks");
                break;
            case "active":
                list = FXCollections.observableList(App.getRegister().getAllTasks().stream()
                        .filter(MainTask -> !MainTask.isDone())
                        .collect(Collectors.toList()));
                header.setText("Viewing all active tasks");
                break;
        }
        tableView.setItems(list);
    }
}
