package ntnu.team1;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.team1.application.fileHandling.Read;
import ntnu.team1.application.task.MainTask;

import java.io.IOException;

public class FinishedController {

    public VBox showTasks;
    private MainRegister register = new MainRegister();

    public void initialize(){
        Read reader = new Read("data/categories.ser","data/tasks.ser");
        register.setCategories(reader.readCategory());
        register.setTasks(reader.readTasks());
        updateTasks();
    }

    public void switchToMain() throws IOException {
        App.setRootWithSave("main",register);
    }

    private void updateTasks(){
        showTasks.getChildren().clear();
        showTasks.setPrefWidth(969);
        for (MainTask t : register.getAllTasks()) {
            if (t.isDone()) {
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

                Label taskName = new Label(t.getName());
                edit(taskName, t.getName());

                Label startDate = new Label(String.valueOf(t.getStartDate()));
                edit(startDate, String.valueOf(t.getStartDate()));

                Label endDate = new Label(String.valueOf(t.getEndDate()));
                edit(endDate, String.valueOf(t.getEndDate()));

                Label description = new Label(t.getDescription());
                edit(description, t.getDescription());

                Label category = new Label(register.getCategory(t.getCategoryId()).getName());
                edit(category, register.getCategory(t.getCategoryId()).getName());

                Label priority = new Label(String.valueOf(t.getPriority()));
                edit(priority, String.valueOf(t.getPriority()));

                Separator separator = new Separator();

                hBox.getChildren().add(checkBox);
                hBox.getChildren().add(taskName);
                hBox.getChildren().add(description);
                hBox.getChildren().add(startDate);
                hBox.getChildren().add(endDate);
                hBox.getChildren().add(priority);
                hBox.getChildren().add(category);

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

                showTasks.getChildren().add(hBox);
                showTasks.getChildren().add(anchorPane);
            }
        }
    }

    private void edit(Label l, String t){
        l.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> l.setText("Edit"));
        l.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> l.setText(t));
    }
}