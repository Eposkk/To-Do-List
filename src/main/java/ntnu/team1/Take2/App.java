package ntnu.team1.Take2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Write;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static MainRegister register = new MainRegister();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainApplication"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRegister(MainRegister register) {
        App.register = register;
    }

    static void setRootWithSave(String fxml, MainRegister register) throws IOException {
        App.register =register;
        Write writer = new Write(register.getCategories(),register.getAllTasks());
        writer.writeCategories();
        writer.writeTasks();
        scene.setRoot(loadFXML(fxml));
    }

    public static MainRegister getRegister(){
        return register;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void stop(){
        System.out.println("Program is closing");
        Write writer = new Write(register.getCategories(), register.getAllTasks());
        writer.writeCategories();
        writer.writeTasks();
    }
}