package ntnu.team1.mainApplication.task;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.mainApplication.App;

import java.io.FileInputStream;
import java.io.IOException;

public class ShowDialogController {

    public static void addNewTask() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "task/newTask.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 800, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void editTask(MainTask task) throws IOException {
        App.getRegister().setSelectedTask(task);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("task/editTask.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 800, 600);
        Stage stage = new Stage();
        stage.setTitle("Edit task");
        stage.getIcons().add(new Image(new FileInputStream("src/main/resources/Images/edit.png")));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
