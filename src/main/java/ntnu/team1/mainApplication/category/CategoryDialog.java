package ntnu.team1.mainApplication.category;


import javafx.collections.FXCollections;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.mainApplication.App;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;

public class CategoryDialog extends Dialog<MainRegister> {

    public enum Mode {
        NEW, EDIT, INFO
    }

    private final Mode mode;

    private Category existingCategory = null;

    /**
     *
     */
    public CategoryDialog() {
        super();
        this.mode = Mode.NEW;
        // Create the content of the dialog
        createPatient();
    }

    public CategoryDialog(Category category, boolean editable) {
        super();
        if (editable) {
            this.mode = Mode.EDIT;
        } else {
            this.mode = Mode.INFO;
        }
        this.existingCategory= category;
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
                setTitle("Edit category");

                break;

            case NEW:
                setTitle("Add category");
                break;

            case INFO:
                setTitle("Category info");
                break;


        }

        // Set the button types.
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        VBox mainBox = new VBox();

        TextField name = new TextField();

        ColorPicker color = new ColorPicker();

        mainBox.getChildren().add(new Label("Name:"));
        mainBox.getChildren().add(name);
        mainBox.getChildren().add(new Label("Color:"));
        mainBox.getChildren().add(color);

        if ((mode == Mode.EDIT) || (mode == Mode.INFO)) {
            name.setText(existingCategory.getName());
            color.setValue(existingCategory.getColor());


            if (mode == Mode.INFO) {

            }
        }


        getDialogPane().setContent(mainBox);
        setResultConverter((ButtonType button) -> {
            MainRegister result = App.getRegister();
            if (button == ButtonType.OK) {
                if (mode == Mode.NEW) {
                    result.addCategory(name.getText(), color.getValue());
                } else if (mode == Mode.EDIT) {
                    result.editCategory(new Category(existingCategory.getID(), color.getValue(), name.getText()));
                }
            }
            return result;
        });
    }
}
