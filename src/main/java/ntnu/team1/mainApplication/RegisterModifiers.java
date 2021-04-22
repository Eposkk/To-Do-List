package ntnu.team1.mainApplication;

import javafx.scene.control.Alert;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.mainApplication.category.CategoryDialog;
import ntnu.team1.mainApplication.task.TaskDialog;

import java.util.Optional;

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
}
