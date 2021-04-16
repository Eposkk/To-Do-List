package ntnu.team1.Take2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class addTaskDialogController {


    public ChoiceBox<String> choiceBox;
    public DatePicker startDate;
    public DatePicker endDate;
    public TextArea description;
    public TextArea taskName;
    public Button cancel;
    public Button submitTask;
    public ToggleGroup priority;


    @FXML
    private void initialize(){
        ArrayList<String> namesOfCategories = (ArrayList<String>) App.getRegister().getCategories().values().stream().map(Category::getName).collect(Collectors.toList());
        choiceBox.setItems(FXCollections.observableArrayList(namesOfCategories));
    }

    @FXML
    private void submit(){
        RadioButton r =(RadioButton) priority.getSelectedToggle();
        int category1 = -1;
        if(choiceBox.getValue() != null){
            category1 = App.getRegister().getCategories().values().stream().filter(Category -> Category.getName().equals(choiceBox.getValue())).findFirst().get().getID();

        }
        MainRegister result = App.getRegister();
        result.addMainTask(startDate.getValue(),endDate.getValue(),taskName.getText(),description.getText(),Integer.parseInt(r.getText()),category1);
        App.setRegister(result);
        Stage stage = (Stage) submitTask.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void cancel(){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }


}
