package ntnu.team1.Take2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Read;
import ntnu.team1.application.task.MainTask;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

public class FinishedController {

    public class ToDoController {

        @FXML
        private javafx.scene.control.TableView<MainTask> TableView;

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


        MainRegister register = App.getRegister();

        public void initialize(){

            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));

            fillTable();

        }

        @FXML
        private void addNewTask() throws IOException {
            App.setRootWithSave("newtask", register);
        }
        private void fillTable(){
            ObservableList<MainTask> registerWrapper = FXCollections.observableArrayList(register.getAllTasks());
            TableView.setItems(registerWrapper);
        }


    }

}
