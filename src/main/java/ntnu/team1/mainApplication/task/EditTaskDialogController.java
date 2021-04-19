package ntnu.team1.mainApplication.task;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.mainApplication.App;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class EditTaskDialogController {

    public ChoiceBox<String> choiceBox;
    public DatePicker startDate;
    public DatePicker endDate;
    public TextArea description;
    public TextArea taskName;
    public Button cancel;
    public Button submitTask;
    public ToggleGroup priority;

    private MainTask selected;
    private MainRegister result;
    @FXML
    private void initialize(){
        result = App.getRegister();
        selected = result.getSelectedMainTask();
        ArrayList<String> namesOfCategories = (ArrayList<String>) App.getRegister().getCategories().values().stream().map(Category::getName).collect(Collectors.toList());
        choiceBox.setItems(FXCollections.observableArrayList(namesOfCategories));
        setTextFields();
    }

    @FXML
    private void submit(){
        RadioButton r =(RadioButton) priority.getSelectedToggle();
        int category1 = -1;
        if(choiceBox.getValue() != null){
            category1 = App.getRegister().getCategories().values().stream().filter(Category -> Category.getName().equals(choiceBox.getValue())).findFirst().get().getID();
        }
        result.editMainTask(startDate.getValue(),endDate.getValue(),taskName.getText(),description.getText(),Integer.parseInt(r.getText()),category1);
        App.setRegister(result);
        Stage stage = (Stage) submitTask.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void cancel(){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
    private void setTextFields(){
        taskName.setText(selected.getName());
        description.setText(selected.getDescription());
        endDate.setValue(selected.getEndDate());
        startDate.setValue(selected.getStartDate());
        priority.selectToggle(priority.getToggles().get(selected.getPriority()-1));
        choiceBox.getSelectionModel().select(selected.getCategoryId());

    }
}

