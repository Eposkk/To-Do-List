package ntnu.team1.Take2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ntnu.team1.Take2.App;
import ntnu.team1.MVP.MainController;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Read;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class AddTaskDialogController {


    public ChoiceBox choiceBox;
    public DatePicker startDate;
    public DatePicker endDate;
    public TextArea description;
    public TextArea taskName;
    public Button cancel;
    public Button submitTask;
    public ToggleGroup priority;

    MainRegister register = new MainRegister();
    ArrayList<Category> categories;
    ArrayList<String> namesOfCategories;

    private ObservableList<MainTask> observableList;

    @FXML
    private void initialize(){
        File category= new File("data/categories.ser");
        if (category.exists()){
            Read reader = new Read("data/categories.ser","data/tasks.ser");
            register.setCategories(reader.readCategory());
            register.setTasks(reader.readTasks());
        }
        categories =  new ArrayList<>(register.getCategories().values());
        namesOfCategories = new ArrayList<>();
        for (Category c: categories){
            namesOfCategories.add(c.getName());
        }
        choiceBox.setItems(FXCollections.observableArrayList(namesOfCategories));
        App.setRegister(register);
    }

    @FXML
    private void submit() throws IOException {
        RadioButton r =(RadioButton) priority.getSelectedToggle();
        int category1 = -1;
        if(choiceBox.getValue() != null){
            for (Category category: categories){
                if(category.getName().equals(choiceBox.getValue().toString())){
                    category1=category.getID();
                }
            }
        }
        register.addMainTask(startDate.getValue(),endDate.getValue(),taskName.getText(),description.getText(),Integer.parseInt(r.getText()),category1);
        observableList = FXCollections.observableArrayList(register.getAllTasks());
        App.updateWrapper(observableList);
        Stage stage = (Stage) submitTask.getScene().getWindow();
        stage.close();
        App.setRootWithSave("MainApplication", register);
    }
    @FXML
    private void cancel(){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void setAppMainObservableList(ObservableList<MainTask> tvObservableList) {
        this.observableList = tvObservableList;

    }
}
