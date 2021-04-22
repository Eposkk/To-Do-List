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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.mainApplication.App;
import ntnu.team1.mainApplication.MainApplicationController;
import ntnu.team1.mainApplication.RegisterModifiers;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ShowByCategoryController {

    @FXML
    private Button addNewTool;

    @FXML
    private Button editTool;

    @FXML
    private Button deleteTool;

    @FXML
    private Button deleteAllTool;

    @FXML
    private ImageView addTaskIcon;

    @FXML
    private ImageView removeTaskIcon;

    @FXML
    private ImageView editTaskIcon;

    @FXML
    private ImageView deleteAllIcon;

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
    private TableColumn<MainTask, Button> deleteButtonColumn;

    @FXML
    private Label header;

    @FXML
    private ToggleGroup choice;


    public void initialize() throws FileNotFoundException {
        choice.selectedToggleProperty().addListener((observableValue, toggle, t1) -> updateList());
        if(App.getChosenCategory() > -1){
            header.setText("Viewing all tasks in category " + App.getRegister().getCategories().get(App.getChosenCategory()).getName());

        }else{
            header.setText("Viewing all tasks without a given category");
        }
        columFactory();
        updateList();
        displayToolbarIcons();
    }

    @FXML
    private void addNewTask(){
        RegisterModifiers.addNewTask();
        updateList();
    }

    @FXML
    private void editTask(){
        RegisterModifiers.editTask(tableView.getSelectionModel().getSelectedItem());
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
        deleteButtonColumn.setCellValueFactory(new PropertyValueFactory<>(""));
    }

    @FXML
    private void displayToolbarIcons() throws FileNotFoundException {
        ArrayList<Button> buttons = MainApplicationController.displayToolbarIcons(addNewTool, deleteTool, editTool ,deleteAllTool);
        addNewTool = buttons.get(0);
        deleteTool = buttons.get(1);
        editTool = buttons.get(2);
        deleteAllTool = buttons.get(3);
    }

    private void updateList(){
        RadioButton r = (RadioButton) choice.getSelectedToggle();
        String selected = r.getText();
        ObservableList<MainTask> list = null;
        switch (selected) {
            case "all":
                list = FXCollections.observableList(App.getRegister().getAllTasks().stream()
                        .filter(MainTask -> MainTask.getCategoryId() == App.getChosenCategory())
                        .collect(Collectors.toList()));
                break;
            case "done":
                list = FXCollections.observableList(App.getRegister().getAllTasks().stream()
                        .filter(MainTask -> MainTask.getCategoryId() == App.getChosenCategory())
                        .filter(MainTask::isDone)
                        .collect(Collectors.toList()));
                break;
            case "active":
                list = FXCollections.observableList(App.getRegister().getAllTasks().stream()
                        .filter(MainTask -> MainTask.getCategoryId() == App.getChosenCategory())
                        .filter(MainTask -> !MainTask.isDone())
                        .collect(Collectors.toList()));
                break;
        }
        tableView.setItems(list);
    }
}
