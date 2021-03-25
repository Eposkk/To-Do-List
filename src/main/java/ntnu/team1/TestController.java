package ntnu.team1;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import ntnu.team1.application.task.MainTask;

import javafx.scene.paint.Color;
import java.util.Random;

public class TestController {


    public VBox taskList;

    MainRegister register = new MainRegister();

    public void start(){
        generateTask();
    }

    @FXML
    public void  generateTask(){

        register.addCategory("Kategori_1", Color.BLUE);
        register.addCategory("Kategori_2", Color.YELLOW);
        register.addCategory("Kategori_3", Color.RED);
        register.addCategory("Kategori_4", Color.GREEN);
        register.addCategory("Kategori_5", Color.PINK);

        for(int i = 0; i<=100;i++) {
            String name = "task " + i;
            String description = "Lorem Ipsum";
            Random random = new Random();
            register.addMainTask(null, null, name, description, random.nextInt(3), random.nextInt(3));
        }

        register.sortByCategory();

        for (MainTask t: register.getAllTasks() ){

            HBox hBox = new HBox();
            Label taskName=new Label(t.getName());
            Label taskDate=new Label(String.valueOf(t.getEndDate()));
            Label description = new Label(t.getDescription());
            Label priority = new Label(String.valueOf(t.getPriority()));

            Background backgroundColor = new Background(new BackgroundFill(register.getCategory(t.getCategoryId()).getColor(), CornerRadii.EMPTY,
                    Insets.EMPTY));
            hBox.setBackground(backgroundColor);

            hBox.getChildren().add(taskName);
            hBox.getChildren().add(taskDate);
            hBox.getChildren().add(description);
            hBox.getChildren().add(priority);
            taskList.getChildren().add(hBox);
        }
    }








}
