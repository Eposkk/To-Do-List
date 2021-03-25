package ntnu.team1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import ntnu.team1.application.task.MainTask;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
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
    private VBox mainVBox;

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
        updateTasks();

    }

    private void updateTasks(){
        showTasks.getChildren().clear();
        showTasks.setPrefWidth(969);
        for (MainTask t: register.getAllTasks() ){

            HBox hBox = new HBox();
            VBox vBox1 = new VBox();
            VBox vBox2 = new VBox();
            VBox vBox3 = new VBox();
            VBox vBox4 = new VBox();
            VBox vBox5 = new VBox();

            AnchorPane anchorPane = new AnchorPane();
            ImageView imageView = new ImageView();

            CheckBox checkBox = new CheckBox();
            Label taskName=new Label(t.getName());
            Label taskDate=new Label(String.valueOf(t.getEndDate()));
            Label description = new Label(t.getDescription());
            Label category = new Label("Testkategori");
            Label priority = new Label(String.valueOf(t.getPriority()));
            Separator separator = new Separator();

            vBox1.getChildren().add(checkBox);
            vBox2.getChildren().add(taskName);
            vBox3.getChildren().add(taskDate);
            vBox4.getChildren().add(category);
            vBox5.getChildren().add(imageView);

            hBox.getChildren().add(vBox1);
            hBox.getChildren().add(vBox2);
            hBox.getChildren().add(vBox3);
            hBox.getChildren().add(vBox4);
            hBox.setPrefHeight(56);

            separator.setOrientation(Orientation.HORIZONTAL);
            separator.setPrefWidth(900);
            separator.setLayoutX(95);
            separator.setLayoutY(108);
            anchorPane.getChildren().add(separator);
            anchorPane.setPrefHeight(3);
            AnchorPane.setLeftAnchor(separator, 80.0);
            AnchorPane.setBottomAnchor(separator, 0.0);
            AnchorPane.setTopAnchor(separator, 0.0);


            vBox1.setPrefWidth(80);
            vBox1.setPrefHeight(54);
            vBox1.setPadding(new Insets(0, 0, 0, 30));
            vBox1.setAlignment(Pos.CENTER);

            vBox2.setPrefWidth(550);
            vBox2.setPrefHeight(54);
            vBox2.setAlignment(Pos.CENTER_LEFT);

            vBox3.setPrefWidth(167);
            vBox3.setPrefHeight(54);
            vBox3.setAlignment(Pos.CENTER);

            vBox3.setPrefWidth(167);
            vBox3.setPrefHeight(54);
            vBox3.setAlignment(Pos.CENTER);

            vBox4.setPrefWidth(167);
            vBox4.setPrefHeight(54);
            vBox4.setAlignment(Pos.CENTER);

            imageView.setFitHeight(29);
            imageView.setFitWidth(30);
            vBox5.setPrefWidth(6);
            vBox5.setPrefHeight(54);
            vBox5.setAlignment(Pos.CENTER);

            showTasks.getChildren().add(hBox);
            showTasks.getChildren().add(anchorPane);
        }
    }
}
