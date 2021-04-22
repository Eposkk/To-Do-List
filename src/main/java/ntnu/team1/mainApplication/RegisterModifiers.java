package ntnu.team1.mainApplication;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.exceptions.RemoveException;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.mainApplication.category.CategoryDialog;
import ntnu.team1.mainApplication.task.TaskDialog;

import java.util.Optional;
import java.util.stream.Collectors;

public class RegisterModifiers {

    public static void editTask(MainTask existingTask){
        if(existingTask == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select a Task:");
            alert.setHeaderText("No task selected" );
            alert.setContentText("Please select a task to edit");
            alert.showAndWait();
        }else{
            TaskDialog patientDialog = new TaskDialog(existingTask, true);
            Optional<MainRegister> result = patientDialog.showAndWait();
            if(result.isPresent()){
                MainRegister updatedRegister = result.get();
                App.setRegister(updatedRegister);
            }
        }
    }

    public static void addNewTask(){
        TaskDialog addNewDialog = new TaskDialog();
        Optional<MainRegister> result = addNewDialog.showAndWait();
        if(result.isPresent()){
            MainRegister register = result.get();
            App.setRegister(register);
        }
    }

    public static void removeTask(MainTask task){
        MainRegister register = App.getRegister();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog - Delete Item");
        alert.setContentText("Are you sure you want to delete this task?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            register.removeMainTask(task.getID());
            App.setRegister(register);
        }
    }

    public static void addNewCategory(){
        CategoryDialog addNewDialog = new CategoryDialog();
        Optional<MainRegister> result = addNewDialog.showAndWait();
        if(result.isPresent()){
            MainRegister register = result.get();
            App.setRegister(register);
        }
    }

    public static void editCategory(Category selectedCategory){
        CategoryDialog editDialog = new CategoryDialog(selectedCategory, true);
        Optional<MainRegister> result = editDialog.showAndWait();
        if(result.isPresent()){
            MainRegister register = result.get();
            App.setRegister(register);
        }
    }

    public static void removeCategory(Category selectedCategory) throws RemoveException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog - Delete Item");
        alert.setContentText("Are you sure you want to delete this category?");
        alert.setContentText("Deleting this category will delete: \n ALL TASKS IN THIS CATEGORY");

        MainRegister register = App.getRegister();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            App.getRegister().getAllTasks().stream()
                    .filter(MainTask -> MainTask.getCategoryId() == selectedCategory.getID())
                    .forEach(MainTask -> register.removeMainTask(MainTask.getID()));
            register.removeCategory(selectedCategory.getID());
            App.setRegister(register);
        }




    }
}
