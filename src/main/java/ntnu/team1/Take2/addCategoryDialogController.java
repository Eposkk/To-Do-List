package ntnu.team1.Take2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.Category;

import java.util.ArrayList;

public class addCategoryDialogController {
    @FXML
    private Button cancel;

    @FXML
    private Button submit;

    @FXML
    private TextField name;

    @FXML
    private ColorPicker colour;

    MainRegister register = new MainRegister();
    ArrayList<Category> categories;
    private ObservableList<Category> observableList;

    @FXML
    private void initialize(){
        register = App.getRegister();
        categories=new ArrayList<>(register.getCategories().values());
    }

    private void submit(){
        register.addCategory(name.getText(), colour.getValue());
        observableList = FXCollections.observableList(new ArrayList<>(register.getCategories().values()));
        App.updateCategoryWrapper(observableList);
        /**/


    }

}
