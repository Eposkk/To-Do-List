package ntnu.team1.mainApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Read;
import ntnu.team1.application.fileHandling.Write;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static MainRegister register = new MainRegister();
    private static int chosenCategory = -1;

    private static boolean taskSelector = false;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        register = getRegisterFromSave();
        scene = new Scene(loadFXML("MainApplication"), 640, 480);
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



    public static void setRegister(MainRegister reg) {
        register = reg;
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