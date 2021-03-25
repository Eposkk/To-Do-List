package ntnu.team1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class TodayController {

    @FXML
    private Button textGenerateButton;
    @FXML
    private Button addNewTaskButton;
    @FXML
    private Text addNewTaskText;
    @FXML
    private Label todayTitleLabel;
    @FXML
    private Text upcomingCategoryText;
    @FXML
    private HBox todayCategoryHBox;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToUpcoming() throws IOException {
        App.setRoot("upcoming");
    }
    @FXML
    public void switchToTest(ActionEvent actionEvent) throws IOException {
        App.setRoot("test");
    }
}
