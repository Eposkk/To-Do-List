package ntnu.team1.mainApplication.task;


import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.mainApplication.App;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class used for creating dialogs for adding or editing Tasks
 */

public class TaskDialog extends Dialog<MainRegister> {

    /**
     * Enumarator for class
     */

    public enum Mode {
        NEW, EDIT
    }

    private final Mode mode;

    private MainTask existingTask = null;

    /**
     * Constructor for TaskDialog
     */
    public TaskDialog() {
        super();
        this.mode = Mode.NEW;
        // Create the content of the dialog
        createTask();
    }

    /**
     * Constructor for TaskDialog
     * @param task task to edit
     * @param editable a boolean indicating if the task should be edited
     */

    public TaskDialog(MainTask task, boolean editable) {
        super();
        this.mode = Mode.EDIT;

        this.existingTask = task;
        // Create the content of the dialog
        createTask();
    }

    /**
     * Creates a task to be showed
     */
    private void createTask() {
        // Set title depending upon mode...
        switch (this.mode) {
            case EDIT:
                setTitle("Task Details - Edit");

                break;

            case NEW:
                setTitle("Task Details - Add");
                break;

            default:
                setTitle("Task Details - UNKNOWN MODE...");
                break;

        }

        // Set the button types.
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("dialog.css")).toExternalForm());
        getDialogPane().getStyleClass().add("dialog");

        HBox mainBox = new HBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();

        TextField name = new TextField();
        name.setPromptText("Add task name");
        TextArea description = new TextArea();
        description.setWrapText(true);
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
        priorityBox.setSpacing(5);
        ChoiceBox<String> category = new ChoiceBox();
        ArrayList<String> namesOfCategories = (ArrayList<String>) App.getRegister().getCategories().values().stream().map(Category::getName).collect(Collectors.toList());
        category.setItems(FXCollections.observableArrayList(namesOfCategories));
        category.setValue(App.getRegister().getCategories().get(App.getChosenCategory()).getName());
        category.setPrefWidth(175);

        DatePicker startDate = new DatePicker();
        DatePicker endDate = new DatePicker();


        if (mode == Mode.EDIT) {
            name.setText(existingTask.getName());
            description.setText(existingTask.getDescription());
            switch (existingTask.getPriority()){
                case "Medium":
                    pri2.setSelected(true);
                    break;
                case "Low":
                    pri3.setSelected(true);
                    break;
            }
            if(App.getRegister().getCategory(existingTask.getCategoryId()) != null){
                category.setValue(App.getRegister().getCategory(existingTask.getCategoryId()).getName());
            }
            startDate.setValue(existingTask.getStartDate());
            endDate.setValue(existingTask.getEndDate());

        }
        vBox1.getChildren().add(name);
        vBox1.getChildren().add(description);
        mainBox.getChildren().add(vBox1);
        vBox2.getChildren().add(new Label("Priority:"));
        vBox2.getChildren().add(priorityBox);
        vBox2.getChildren().add(category);
        vBox2.getChildren().add(new Label("Start date"));
        vBox2.getChildren().add(startDate);
        vBox2.getChildren().add(new Label("End date"));
        vBox2.getChildren().add(endDate);
        vBox2.setSpacing(5);
        mainBox.getChildren().add(vBox2);
        mainBox.setSpacing(15);

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