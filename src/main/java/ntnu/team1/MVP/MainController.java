package ntnu.team1.MVP;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Read;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;

import java.io.File;
import java.io.IOException;
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
    public DatePicker startDate;
    public ToggleGroup priority;
    public VBox showTasks;
    public HBox addNewTaskHBox;
    public VBox allCategoriesVBox;


    MainRegister register = new MainRegister();


    @FXML
    private void initialize(){
        File category= new File("data/categories.ser");
        if (category.exists()){
            Read reader = new Read("data/categories.ser","data/tasks.ser");
            register.setCategories(reader.readCategory());
            register.setTasks(reader.readTasks());
            updateTasks();
        }
        App.setReg(register);

        for(int i=0; i<register.getCategories().size();i++){
            Label l = new Label(register.getCategories().get(i).getName()); l.setId("labelCategoryBox");
            HBox c=new HBox(l); c.setId("categoryBox");
            allCategoriesVBox.getChildren().add(c);
            allCategoriesVBox.getChildren().add(new Separator());
        }
        Label l = new Label("Add new category");
        l.setId("labelCategoryBox");
        HBox c=new HBox(l);
        c.setId("categoryBox");
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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "newTask.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 800, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
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
        showTasks.setPrefWidth(1000);

        for (MainTask t: register.getAllTasks() ){

            if(!t.isDone()){
                VBox vBox = new VBox();
                vBox.setId("taskHolder");
                HBox hBox = new HBox();
                hBox.setId("taskBox");

                CheckBox checkBox = new CheckBox();
                checkBox.setId(String.valueOf(t.getID()));
                checkBox.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                    t.setDone(true);
                    updateTasks();
                });
                hBox.getChildren().add(checkBox);

                Label taskName=new Label(t.getName());
                showEditOption(taskName, t.getName());
                edit(taskName, t,1,vBox);
                hBox.getChildren().add(taskName);

                Label description = new Label(t.getDescription());
                showEditOption(description, t.getDescription());
                edit(description, t,2, vBox);
                hBox.getChildren().add(description);

                Label startDate=new Label(String.valueOf(t.getStartDate()));
                showEditOption(startDate, String.valueOf(t.getStartDate()));
                edit(startDate, t,3, vBox);
                hBox.getChildren().add(startDate);

                Label endDate=new Label(String.valueOf(t.getEndDate()));
                showEditOption(endDate, String.valueOf(t.getEndDate()));
                edit(endDate, t,4, vBox);
                hBox.getChildren().add(endDate);

                Label priority = new Label(String.valueOf(t.getPriority()));
                showEditOption(priority, String.valueOf(t.getPriority()));
                edit(priority, t,5, vBox);
                hBox.getChildren().add(priority);

                Label category = new Label("No Category");
                showEditOption(category, "No category");
                if(t.hasCategory()){
                    category = new Label(register.getCategory(t.getCategoryId()).getName());
                    showEditOption(category, register.getCategory(t.getCategoryId()).getName());
                }
                edit(category, t, 6, vBox);
                hBox.getChildren().add(category);


                Button delete = new Button("Delete");
                delete.setOnAction(event ->{
                    register.removeMainTask(t.getID());
                    updateTasks();
                });
                hBox.getChildren().add(delete);

                AnchorPane anchorPane = new AnchorPane();
                Separator separator = new Separator();
                anchorPane.getChildren().add(separator);
                AnchorPane.setLeftAnchor(separator, 80.0);
                AnchorPane.setBottomAnchor(separator, 0.0);
                AnchorPane.setTopAnchor(separator, 0.0);

                vBox.getChildren().add(hBox);
                showTasks.getChildren().add(vBox);
                showTasks.getChildren().add(anchorPane);
            }
        }
    }

    @FXML
    private void switchToCategories() throws IOException {
        App.setRootWithSave("category", register);
    }
}
