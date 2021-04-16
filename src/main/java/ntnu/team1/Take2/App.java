package ntnu.team1.Take2;

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
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static MainRegister register = new MainRegister();
    public static ObservableList<MainTask> taskRegisterWrapper = FXCollections.observableArrayList(register.getAllTasks().stream().filter(MainTask -> MainTask.isDone()==false).collect(Collectors.toList()));
    public static ObservableList<Category> categoryRegisterWrapper = FXCollections.observableArrayList(new ArrayList<>(register.getCategories().values()));

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        register = getRegisterFromSave();

        taskRegisterWrapper = FXCollections.observableArrayList(register.getAllTasks().stream().filter(MainTask -> MainTask.isDone()==false).collect(Collectors.toList()));

        scene = new Scene(loadFXML("MainApplication"), 640, 480);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static ObservableList<MainTask> getWrapper(){
        return taskRegisterWrapper;
    }

    public static void updateTaskWrapper(ObservableList<MainTask> o){
        taskRegisterWrapper = o;
    }

    public static void updateCategoryWrapper(ObservableList<Category> o){
        categoryRegisterWrapper = o;
    }

    public static void changeWrapper(boolean isDone){
        taskRegisterWrapper = FXCollections.observableArrayList(register.getAllTasks().stream().filter(MainTask -> MainTask.isDone()==isDone).collect(Collectors.toList()));

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

    public static MainRegister getRegisterFromSave (){
        MainRegister registerLocal = new MainRegister();
        File category= new File("data/categories.ser");
        if (category.exists()){
            Read reader = new Read("data/categories.ser","data/tasks.ser");
            registerLocal.setCategories(reader.readCategory());
            registerLocal.setTasks(reader.readTasks());
        }
        return registerLocal;
    }


    @Override
    public void stop(){
        System.out.println("Program is closing");
        System.out.println("This was run in App.java");
        Write writer = new Write(register.getCategories(), register.getAllTasks());
        writer.writeCategories();
        writer.writeTasks();
    }
}