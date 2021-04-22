package ntnu.team1.mainApplication;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Read;
import ntnu.team1.application.fileHandling.Write;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App, this is the main class of the application.
 * Handles storing the register and saving and reading to files.
 * Also loads fxml file
 */
public class App extends Application {

    private static MainRegister register = new MainRegister();
    private static int chosenCategory = -1;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        register = getRegisterFromSave();
        Scene scene = new Scene(loadFXML("MainApplication"), 640, 480);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setTitle("To-Do-List 1.0");
        stage.getIcons().add(new Image(new FileInputStream("src/main/resources/ntnu/team1/mainApplication/Logo256px.png")));
        stage.show();
    }

    public static void setChosenCategory(int id){
        chosenCategory = id;
    }
    public static int getChosenCategory(){
        return chosenCategory;
    }

    public static void setRegister(MainRegister register) {
        App.register = register;
    }

    public static MainRegister getRegister(){
        return register;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static MainRegister getRegisterFromSave(){
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