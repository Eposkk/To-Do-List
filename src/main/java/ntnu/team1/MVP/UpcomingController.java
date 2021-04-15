package ntnu.team1.MVP;

import javafx.fxml.FXML;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;

public class UpcomingController {

    @FXML
    private Text upcomingTitleText;
    @FXML
    private Text todayCategoryText;
    @FXML
    private Text upcomingCategoryText;
    @FXML
    private Text monText;
    @FXML
    private Text monDate;
    @FXML
    private Circle dotMonday;


    @FXML
    private void viewMonday() throws IOException{
        dotMonday.setVisible(true);
        monDate.setFill(Paint.valueOf("#ff0000"));
    }

    @FXML
    private void viewItemsMonday() {

    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToToday() throws IOException {
        App.setRoot("today");
    }
}