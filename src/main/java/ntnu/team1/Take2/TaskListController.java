package ntnu.team1.Take2;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ntnu.team1.Take2.App;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Read;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;

import java.io.IOException;
import java.time.LocalDate;

public class TaskListController {

    @FXML
    private javafx.scene.control.TableView<MainTask> TableView;

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


    ObservableList<MainTask> registerWrapper = App.getTaskWrapper();

    public void initialize(){
        columFactory();
        updateList();
    }

    @FXML
    private void addNewTask() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "newtask.fxml"));
        Parent parent = fxmlLoader.load();
        AddTaskDialogController dialogController = new AddTaskDialogController();
        TaskListController toDoController = new TaskListController();
        dialogController.setAppMainObservableList(toDoController.getRegisterWrapper());

        Scene scene = new Scene(parent, 800, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void removeTask(){
        registerWrapper.remove(TableView.getSelectionModel().getSelectedItem());
    }

    private void columFactory(){
        doneColumn.setCellFactory(column -> new CheckBoxTableCell<>());
        doneColumn.setCellValueFactory(cellData -> {
            MainTask task = cellData.getValue();
            BooleanProperty property = new SimpleBooleanProperty(task.isDone());

            property.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->{
                task.setDone(newValue);
                App.updateTaskWrapper(registerWrapper);
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

    private void updateList(){
        registerWrapper= App.getTaskWrapper();
        TableView.setItems(registerWrapper);
    }

    public ObservableList<MainTask> getRegisterWrapper() {
        return registerWrapper;
    }
}
