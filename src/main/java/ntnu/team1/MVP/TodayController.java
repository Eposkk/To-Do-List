package ntnu.team1.MVP;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class TodayController {

    /*@FXML
    public ImageView todayImage;
    @FXML
    public ImageView upcomingImage;*/
    @FXML
    public Label testLabel;
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
    private TextField addNewTaskTextField;
    @FXML
    private TextField addNewDeadlineTextField;
    @FXML
    private TextField addNewCategoryTextField;
    @FXML
    private VBox mainTaskVBox;


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void addNewTask() {
        CheckBox checkBox = new CheckBox();
        HBox hbox1 = new HBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        VBox vBox3 = new VBox();
        VBox vBox4 = new VBox();
        VBox vBox5 = new VBox();

        Label newTaskName = new Label(addNewTaskTextField.getText());
        Label newDeadline = new Label(addNewDeadlineTextField.getText());
        Label newCategory = new Label(addNewCategoryTextField.getText());

        vBox1.getChildren().add(checkBox);
        vBox2.getChildren().add(newTaskName);
        vBox3.getChildren().add(newDeadline);
        vBox4.getChildren().add(newCategory);

        hbox1.getChildren().add(0, vBox1);
        hbox1.getChildren().add(1, vBox2);
        hbox1.getChildren().add(2, vBox3);
        hbox1.getChildren().add(3, vBox4);

        vBox1.setPrefHeight(100);
        vBox1.setPrefWidth(172);
        hbox1.setPrefHeight(35);
        hbox1.setPrefWidth(852);

        mainTaskVBox.getChildren().add(hbox1);
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
