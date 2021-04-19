package ntnu.team1.mainApplication.task;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.mainApplication.App;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskListController {

    public AnchorPane Pane;
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
    private TableColumn<MainTask, Integer> categoryColumn;

    @FXML
    private TableColumn<MainTask, Button> deleteButtonColumn;

    @FXML
    private ToggleGroup choice;

    @FXML
    private Label header;

    public void initialize(){
        choice.selectedToggleProperty().addListener((observableValue, toggle, t1) -> updateList());
        header.setText("Viewing all tasks");
        columFactory();
        updateList();
    }

    @FXML
    private void addNewTask() throws IOException {
        Buttons.addNewTask();
        updateList();
    }

    @FXML
    private void editTask() throws IOException{
        Buttons.editTask(tableView.getSelectionModel().getSelectedItem());
        updateList();
    }

    @FXML
    private void removeTask(){
        MainRegister result = App.getRegister();
        result.removeMainTask(tableView.getSelectionModel().getSelectedItem().getID());
        App.setRegister(result);
        updateList();
    }

    private void columFactory(){
        doneColumn.setCellFactory(column -> new CheckBoxTableCell<>());
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
        deleteButtonColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
    }

    void updateList(){
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
