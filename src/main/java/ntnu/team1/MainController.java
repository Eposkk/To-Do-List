package ntnu.team1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import ntnu.team1.application.task.MainTask;

import java.io.IOException;
import java.time.LocalDate;

public class MainController {

    @FXML
    public ImageView todayImage;
    @FXML
    public ImageView upcomingImage;
    @FXML
    public GridPane gridPane;
    @FXML
    public Label testLabel;
    @FXML
    public TextField newDescriptionTextField;
    public Label todayCategoryText;
    public HBox upcomingCategoryHBox;
    public Label todayCategoryText1;
    public TextArea taskName;
    public TextArea description;
    public DatePicker endDate;
    public Button submitTask;
    public DatePicker startDate;
    public RadioButton pri3;
    public RadioButton pri2;
    public RadioButton pri1;
    public ChoiceBox choiceBox;
    public ToggleGroup priority;
    public VBox showTasks;
    public HBox addNewTaskHBox;

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


    MainRegister register=new MainRegister();


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void addNewTask(){

    }

    @FXML
    private void generateRow() {
        MainTask mainTask = new MainTask(1, LocalDate.now(), LocalDate.of(2021, 03, 26), "Test", "Dette er en test", 2);
        Label newDescription = new Label(newDescriptionTextField.getText());

        this.gridPane.addRow(9,newDescription);
    }

    @FXML
    private void switchToUpcoming() throws IOException {
        App.setRoot("upcoming");
    }
    @FXML
    public void switchToTest(ActionEvent actionEvent) throws IOException {
        App.setRoot("test");
    }

    @FXML
    private void submitTask(){
        RadioButton r =(RadioButton) priority.getSelectedToggle();
        register.addMainTask(startDate.getValue(),endDate.getValue(),taskName.getText(),description.getText(),Integer.parseInt(r.getText()),-1);
        showTasks.getChildren().add(addNewTaskHBox);

    }

    private void updateTasks(){
        for (MainTask t: register.getAllTasks() ){

            HBox hBox = new HBox();
            Label taskName=new Label(t.getName());
            Label taskDate=new Label(String.valueOf(t.getEndDate()));
            Label description = new Label(t.getDescription());
            Label priority = new Label(String.valueOf(t.getPriority()));

            hBox.getChildren().add(taskName);
            hBox.getChildren().add(taskDate);
            hBox.getChildren().add(description);
            hBox.getChildren().add(priority);
            showTasks.getChildren().add(hBox);
        }
    }
}
