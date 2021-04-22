package ntnu.team1.mainApplication.task;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.mainApplication.App;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class TaskDialog extends Dialog<MainRegister> {

    public enum Mode {
        NEW, EDIT, INFO
    }

    private final Mode mode;

    private MainTask existingTask = null;

    /**
     *
     */
    public TaskDialog() {
        super();
        this.mode = Mode.NEW;
        // Create the content of the dialog
        createPatient();
    }

    public TaskDialog(MainTask task, boolean editable) {
        super();
        if (editable) {
            this.mode = Mode.EDIT;
        } else {
            this.mode = Mode.INFO;
        }
        this.existingTask = task;
        // Create the content of the dialog
        createPatient();
    }

    /**
     *
     */
    private void createPatient() {
        // Set title depending upon mode...
        switch (this.mode) {
            case EDIT:
                setTitle("Task Details - Edit");

                break;

            case NEW:
                setTitle("Task Details - Add");
                break;

            case INFO:
                setTitle("Task Details");
                break;

            default:
                setTitle("Patient Details - UNKNOWN MODE...");
                break;

        }

        // Set the button types.
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        HBox mainBox = new HBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        VBox vBox3 = new VBox();


        TextField name = new TextField();
        name.setPromptText("Add task name");
        TextArea description = new TextArea();
        description.setPromptText("Description");
        description.setMaxWidth(200);

        HBox priorityBox = new HBox();
        final ToggleGroup priority = new ToggleGroup();
        RadioButton pri1 = new RadioButton("1");
        pri1.setToggleGroup(priority);
        pri1.setSelected(true);
        priorityBox.getChildren().add(pri1);
        RadioButton pri2 = new RadioButton("2");
        pri2.setToggleGroup(priority);
        priorityBox.getChildren().add(pri2);
        RadioButton pri3 = new RadioButton("3");
        pri3.setToggleGroup(priority);
        priorityBox.getChildren().add(pri3);
        ChoiceBox<String> category = new ChoiceBox();
        ArrayList<String> namesOfCategories = (ArrayList<String>) App.getRegister().getCategories().values().stream().map(Category::getName).collect(Collectors.toList());
        category.setItems(FXCollections.observableArrayList(namesOfCategories));

        DatePicker startDate = new DatePicker();
        DatePicker endDate = new DatePicker();


        if ((mode == Mode.EDIT) || (mode == Mode.INFO)) {
            name.setText(existingTask.getName());
            description.setText(existingTask.getDescription());
            category.setValue(App.getRegister().getCategory(existingTask.getCategoryId()).getName());


            if (mode == Mode.INFO) {
                name.setDisable(true);
                name.setOpacity(0.8);
                description.setDisable(true);
                description.setOpacity(0.8);
            }
        }
        vBox1.getChildren().add(name);
        vBox1.getChildren().add(new Label("Priority:"));
        vBox1.getChildren().add(priorityBox);
        mainBox.getChildren().add(vBox1);
        vBox2.getChildren().add(description);
        mainBox.getChildren().add(vBox2);
        vBox3.getChildren().add(category);
        vBox3.getChildren().add(new Label("Start date"));
        vBox3.getChildren().add(startDate);
        vBox3.getChildren().add(new Label("End date"));
        vBox3.getChildren().add(endDate);
        mainBox.getChildren().add(vBox3);

        getDialogPane().setContent(mainBox);
        setResultConverter((ButtonType button) -> {
            int category1 = -1;
            if(category.getValue() != null){
                category1 = App.getRegister().getCategories().values().stream().filter(Category -> Category.getName().equals(category.getValue())).findFirst().get().getID();

            }
            RadioButton selectedPriority = (RadioButton) priority.getSelectedToggle();
            MainRegister result = App.getRegister();
            if (button == ButtonType.OK) {
                if (mode == Mode.NEW) {
                    result.addMainTask(startDate.getValue(), endDate.getValue(),
                            name.getText(), description.getText(),
                            Integer.parseInt(selectedPriority.getText()),
                            category1);
                } else if (mode == Mode.EDIT) {
                    result.editMainTask(existingTask.getID(), startDate.getValue(), endDate.getValue(),
                            name.getText(), description.getText(),
                            Integer.parseInt(selectedPriority.getText()),
                            category1);
                }
            }
            return result;
        });
    }
}
