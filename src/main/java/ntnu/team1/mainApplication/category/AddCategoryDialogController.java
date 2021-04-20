package ntnu.team1.mainApplication.category;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import ntnu.team1.application.MainRegister;
import ntnu.team1.mainApplication.App;

public class AddCategoryDialogController {

    @FXML
    private Button submit;

    @FXML
    private TextField name;

    @FXML
    private ColorPicker colour;



    @FXML
    private void submit() {
        MainRegister result = App.getRegister();
        result.addCategory(name.getText(), colour.getValue());
        App.setRegister(result);

        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel(){
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }
}
