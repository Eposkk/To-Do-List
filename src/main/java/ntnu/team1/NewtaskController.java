package ntnu.team1;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Read;
import ntnu.team1.application.task.Category;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NewtaskController {
    public ToggleGroup priority;
    public ChoiceBox choiceBox;
    public DatePicker startDate;
    public DatePicker endDate;
    public TextArea description;
    public TextArea taskName;
    public Button cancel;
    public Button submitTask;
    MainController main = new MainController();
    MainRegister register = new MainRegister();
    ArrayList<Category> categories;
    ArrayList<String> namesOfCategories;

    @FXML
    private void initialize(){
        File category= new File("data/categories.ser");
        if (category.exists()){
            Read reader = new Read("data/categories.ser","data/tasks.ser");
            register.setCategories(reader.readCategory());
            register.setTasks(reader.readTasks());
        }
        register.addCategory("Test",null);
        register.addCategory("Skole", null);
        categories =  new ArrayList<>(register.getCategories().values());
        namesOfCategories = new ArrayList<>();
        for (Category c: categories){
            namesOfCategories.add(c.getName());
        }
        choiceBox.setItems(FXCollections.observableArrayList(namesOfCategories));
        App.setReg(register);
    }

    @FXML
    public void cancel() throws IOException {
        App.setRoot("main");
    }
    @FXML
    public void submit() throws IOException {

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
        App.setRootWithSave("main", register);
    }
}
