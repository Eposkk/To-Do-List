package ntnu.team1.Take2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.Category;

import java.io.IOException;
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
    @FXML
    private void submit() throws IOException {
        register.addCategory(name.getText(), colour.getValue());
        observableList = FXCollections.observableList(new ArrayList<>(register.getCategories().values()));
        App.updateCategoryWrapper(observableList);
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
        App.setRootWithSave("MainApplication",register);
    }
    @FXML
    private void cancel(){
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }

    public void setAppCategoryObservableList(ObservableList<Category> tvObservableList) {
        this.observableList = tvObservableList;
    }
}
