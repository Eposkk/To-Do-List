package ntnu.team1;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Read;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


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
    public ChoiceBox choiceBox;
    public ToggleGroup priority;
    public VBox showTasks;
    public HBox addNewTaskHBox;
    public VBox allCategoriesVBox;
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

    MainRegister register = new MainRegister();
    private VBox vBoxPriority;
    ArrayList<Category> categories;
    ArrayList<String> namesOfCategories;

    @FXML
    private void initialize(){
        File category= new File("data/categories.ser");
        if (category.exists()){
            Read reader = new Read("data/categories.ser","data/tasks.ser");
            register.setCategories(reader.readCategory());
            register.setTasks(reader.readTasks());
            updateTasks();
        }
        categories =  new ArrayList<>(register.getCategories().values());
        namesOfCategories = new ArrayList<>();
        for (Category c: categories){
            namesOfCategories.add(c.getName());
        }
        choiceBox.setItems(FXCollections.observableArrayList(namesOfCategories));
        App.setReg(register);

        for(int i=0; i<register.getCategories().size();i++){
            Label l = new Label(register.getCategories().get(i).getName()); l.setId("labelCategoryBox");
            HBox c=new HBox(l); c.setId("categoryBox");
            allCategoriesVBox.getChildren().add(c);
            allCategoriesVBox.getChildren().add(new Separator());
        }
        Label l = new Label("Add new category"); l.setId("labelCategoryBox");
        HBox c=new HBox(l); c.setId("categoryBox");
        c.addEventHandler(MouseEvent.MOUSE_CLICKED,
                mouseEvent -> {
                    try {
                        App.setRootWithSave("category",register);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        allCategoriesVBox.getChildren().add(c);
    }

    @FXML
    private void addNewTask() throws IOException {
        App.setRootWithSave("newtask", register);
    }

    @FXML
    private void switchToFinished() throws IOException {
        App.setRootWithSave("finished", register);
    }

    public MainRegister getRegister() {
        return register;
    }

    @FXML
    private void switchToUpcoming() throws IOException {
        App.setRoot("upcoming");
    }

    @FXML
    private void submitTask(){
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
        updateTasks();
    }

    private void showEditOption(Label l, String t){
        l.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> l.setText("Edit"));
        l.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> l.setText(t));
    }

    private void edit(Label l, MainTask t, int i, VBox vBox){
        HBox newDataBox = new HBox();
        Button newDataButton = new Button();
        newDataButton.setText("Update");
        switch(i){
            case 1:
                newDataBox.getChildren().clear();
                TextField newData = new TextField();
                newDataBox.getChildren().add(newData);
                newDataButton.setOnAction(event -> {
                    t.setName(newData.getText());
                    updateTasks();
                });
                break;
            case 2:
                newDataBox.getChildren().clear();
                newData = new TextField();
                newDataBox.getChildren().add(newData);
                newDataButton.setOnAction(event -> {
                    t.setDescription(newData.getText());
                    updateTasks();
                });
                break;
            case 3:
                newDataBox.getChildren().clear();
                DatePicker newDate = new DatePicker();
                newDataBox.getChildren().add(newDate);
                newDataButton.setOnAction(event -> {
                    t.setStartDate(newDate.getValue());
                    updateTasks();
                });
                break;
            case 4:
                newDataBox.getChildren().clear();
                newDate = new DatePicker();
                newDataBox.getChildren().add(newDate);
                newDataButton.setOnAction(event -> {
                    t.setEndDate(newDate.getValue());
                    updateTasks();
                });
                break;
            case 5:
                newDataBox.getChildren().clear();
                ToggleGroup group = new ToggleGroup();
                for(int j = 1; j<=3; j++) {
                    RadioButton b = new RadioButton(String.valueOf(j));
                    b.setToggleGroup(group);
                    if(j==t.getPriority()){
                        b.setSelected(true);
                    }
                    b.setOnAction(event ->{
                        t.setPriority(Integer.parseInt(b.getText()));
                    updateTasks();});
                    newDataBox.getChildren().add(b);
                }
                return;
            case 6:
                break;


        }
        newDataBox.getChildren().add(newDataButton);
        l.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> vBox.getChildren().add(newDataBox));

    }


    public void updateTasks(){
        showTasks.getChildren().clear();
        showTasks.setPrefWidth(969);
        for (MainTask t: register.getAllTasks() ){

            if(!t.isDone()){
                VBox vBox = new VBox();
                vBox.setId("taskHolder");
                HBox hBox = new HBox();
                hBox.setId("taskBox");

                AnchorPane anchorPane = new AnchorPane();
                ImageView imageView = new ImageView();

                CheckBox checkBox = new CheckBox();
                checkBox.setId(String.valueOf(t.getID()));
                checkBox.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                    t.setDone(true);
                    updateTasks();
                });

                Label taskName=new Label(t.getName());
                showEditOption(taskName, t.getName());
                edit(taskName, t,1,vBox);

                Label description = new Label(t.getDescription());
                showEditOption(description, t.getDescription());
                edit(description, t,2, vBox);

                Label startDate=new Label(String.valueOf(t.getStartDate()));
                showEditOption(startDate, String.valueOf(t.getStartDate()));
                edit(startDate, t,3, vBox);

                Label endDate=new Label(String.valueOf(t.getEndDate()));
                showEditOption(endDate, String.valueOf(t.getEndDate()));
                edit(endDate, t,4, vBox);

                Label priority = new Label(String.valueOf(t.getPriority()));
                showEditOption(priority, String.valueOf(t.getPriority()));
                edit(priority, t,5, vBox);


                Button delete = new Button("Delete");
                delete.setOnAction(event ->{
                    register.removeMainTask(t.getID());
                    updateTasks();
                }
                );

                Separator separator = new Separator();

                hBox.getChildren().add(checkBox);
                hBox.getChildren().add(taskName);
                hBox.getChildren().add(description);
                hBox.getChildren().add(startDate);
                hBox.getChildren().add(endDate);
                hBox.getChildren().add(priority);
                if(t.hasCategory()){
                    Label category = new Label(register.getCategory(t.getCategoryId()).getName());
                    showEditOption(category, register.getCategory(t.getCategoryId()).getName());
                    edit(category, t, 6, vBox);
                    hBox.getChildren().add(category);
                }
                hBox.getChildren().add(delete);

                separator.setOrientation(Orientation.HORIZONTAL);
                separator.setPrefWidth(900);
                separator.setLayoutX(95);
                separator.setLayoutY(108);
                anchorPane.getChildren().add(separator);
                anchorPane.setPrefHeight(3);
                AnchorPane.setLeftAnchor(separator, 80.0);
                AnchorPane.setBottomAnchor(separator, 0.0);
                AnchorPane.setTopAnchor(separator, 0.0);

                checkBox.setPrefWidth(80);
                checkBox.setPrefHeight(54);
                checkBox.setPadding(new Insets(0, 0, 0, 30));
                checkBox.setAlignment(Pos.CENTER);

                imageView.setFitHeight(29);
                imageView.setFitWidth(30);

                vBox.getChildren().add(hBox);
                showTasks.getChildren().add(vBox);
                showTasks.getChildren().add(anchorPane);
            }
        }
    }
}
