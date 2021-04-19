package ntnu.team1.mainApplication;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Read;
import ntnu.team1.application.fileHandling.Write;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static MainRegister register = new MainRegister();
    private static ObservableList<MainTask> taskRegisterWrapper;
    private static ObservableList<Category> categoryRegisterWrapper;

    private static boolean taskSelector = false;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        register = getRegisterFromSave();
        updateTaskWrapper();
        updateCategoryWrapper();
        scene = new Scene(loadFXML("MainApplication"), 640, 480);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static ObservableList<MainTask> getTaskWrapper(){
        return taskRegisterWrapper;
    }

    public static void updateTaskWrapper(){
        taskRegisterWrapper =  FXCollections.observableArrayList(register.getAllTasks().stream().filter(MainTask -> MainTask.isDone()==taskSelector).collect(Collectors.toList()));
    }

    public static ObservableList<Category> getCategoryWrapper() {
        return categoryRegisterWrapper;
    }

    public static void updateCategoryWrapper(){
        categoryRegisterWrapper =  FXCollections.observableArrayList(register.getCategories().values());
    }

    public static void changeTaskWrapper(boolean isDone){
       taskSelector = isDone;
       updateTaskWrapper();
    }

    public static void setRegister(MainRegister reg) {
        register = reg;
        updateTaskWrapper();
        updateCategoryWrapper();
    }

    public static MainRegister getRegister(){
        return register;
    }

    public static void setRootWithSave(String fxml, MainRegister register) throws IOException {
        App.register =register;
        Write writer = new Write(register);
        writer.writeRegister();
        scene.setRoot(loadFXML(fxml));
    }



    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static MainRegister getRegisterFromSave(){
        MainRegister registerLocal = new MainRegister();
        File register= new File("data/mainRegister.ser");
        if (register.exists()){
            System.out.println("Register exists");
            Read reader = new Read("data/mainRegister.ser");
            registerLocal= reader.readRegister();
        }
        return registerLocal;
    }


    @Override
    public void stop(){
        System.out.println("Program is closing");
        System.out.println("This was run in App.java");
        Write writer = new Write(register);
        writer.writeRegister();

    }
}