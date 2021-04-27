package ntnu.team1.application.category;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import ntnu.team1.backend.MainRegister;
import ntnu.team1.backend.objects.Category;
import ntnu.team1.application.main.App;

/**
 * Class for constructing and showing the different category dialogs
 * Extends Dialog
 */

public class CategoryDialog extends Dialog<MainRegister> {

    /**
     * Enumarator for the class
     */

    public enum Mode {
        /**
         * Indicates it is a new category
         */
        NEW,
        /**
         * Indicates it should be edited
         */
        EDIT,
        /**
         * Indicates just show info
         */
        INFO
    }

    private final Mode mode;

    private Category existingCategory = null;

    /**
     *Constructor for the object
     */
    public CategoryDialog() {
        super();
        this.mode = Mode.NEW;
        // Create the content of the dialog
        createCategory();
    }

    /**
     *Constructor for the object
     * @param category Category
     * @param editable If it should be editable or not
     */

    public CategoryDialog(Category category, boolean editable) {
        super();
        if (editable) {
            this.mode = Mode.EDIT;
        } else {
            this.mode = Mode.INFO;
        }
        this.existingCategory= category;
        // Create the content of the dialog
        createCategory();
    }

    /**
     * Method for creating a category
     */
    private void createCategory() {
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
            if(existingCategory.getID() == -1) {
                name.setDisable(true);
                color.setDisable(true);
            }
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
