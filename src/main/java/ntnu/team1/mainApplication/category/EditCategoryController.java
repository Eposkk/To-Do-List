package ntnu.team1.mainApplication.category;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.Category;
import ntnu.team1.mainApplication.App;

public class EditCategoryController {

    @FXML
    private Button submit;

    @FXML
    private TextField name;

    @FXML
    private ColorPicker colour;

    private Category selected;
    private MainRegister result;

    @FXML
    private void initialize(){
        result = App.getRegister();
        selected = result.getSelectedCategory();
        setTextField();
    }

    @FXML
    private void submit() {
        MainRegister result = App.getRegister();
        result.editCategory(name.getText(), colour.getValue());
        App.setRegister(result);
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel(){
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }

    private void setTextField(){
        name.setText(selected.getName());
        colour.setValue(selected.getColor());
    }

}
