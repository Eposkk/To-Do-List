package ntnu.team1.application.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ntnu.team1.backend.MainRegister;
import ntnu.team1.backend.persistence.Read;
import ntnu.team1.backend.persistence.Write;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * JavaFX App, this is the main class of the application.
 * Handles storing the register and saving and reading to files.
 * Also loads fxml file
 */
public class App extends Application {

    /**
     * Main register used for the app
     */
    private static MainRegister register = new MainRegister();
    /**
     * Indicates what category is chosen
     */
    private static int chosenCategory = -1;

    /**
     * Main method
     *
     * @param args launch arguments
     */

    public static void main(String[] args) {
        launch();
    }

    /**
     * Runs on the start of the application. Loads the scene and configures it
     * @param stage main stage
     * @throws IOException gets thrown if something fails with loading external files
     */

    @Override
    public void start(Stage stage) throws IOException {
        register = getRegisterFromSave();
        Scene scene = new Scene(loadFXML("MainApplication"), 640, 480);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setTitle("To-Do-List 1.0");
        stage.getIcons().add(new Image(new FileInputStream("src/main/resources/Images/Logo256pxv2.png")));
        stage.show();
        stage.setOnCloseRequest(event -> {
            alertOnExit();
            event.consume();
        });
    }

    /**
     * Sets the chosen category
     *
     * @param id Integer
     */


    public static void setChosenCategory(int id) {
        chosenCategory = id;
    }

    /**
     * Gets the chosen category
     *
     * @return Integer
     */

    public static int getChosenCategory() {
        return chosenCategory;
    }

    /**
     * Sets the register
     *
     * @param register MainRegister
     */

    public static void setRegister(MainRegister register) {
        App.register = register;
    }

    /**
     * Gets the register
     *
     * @return Returns the MainRegister
     */

    public static MainRegister getRegister() {
        return register;
    }

    /**
     * Class used for loading fxml files
     *
     * @param fxml A string withe the path to the fxml file
     * @return the Parent
     * @throws IOException If it cant find the fxml file an IOException is thrown
     */

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Gets the register from save. Uses the Read class to load in the Register from the serialized file
     *
     * @return the MainRegister loaded from save
     */

    private static MainRegister getRegisterFromSave() {
        MainRegister registerLocal = new MainRegister();
        File register = new File("data/mainRegister.ser");
        if (register.exists()) {
            Read reader = new Read("data/mainRegister.ser");
            registerLocal = reader.readRegister();
        }
        return registerLocal;
    }

    /**
     * Gets called whenever a request is made tro close the application
     * Makes sure that was intended and saves the register
     */

    public static void alertOnExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Do you want to exit the application?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Write writer = new Write(register);
            writer.writeRegister();
            System.exit(1);
        }
    }
}
