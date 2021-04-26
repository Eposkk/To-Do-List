package ntnu.team1.mainApplication.task;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StaticMethods {

    /**
     * /**
     * Adds the images to buttons
     * @param path path Path of the images
     * @param button button The button to add the images to
     * @param width width of the image
     * @param height height of the image
     * @throws FileNotFoundException Throws if file is not found
     */

    public static void addImageToButton(String path, Button button, int width, int height) throws FileNotFoundException {
        FileInputStream inputAdd = new FileInputStream(path);
        Image imageAdd = new Image(inputAdd);
        ImageView icon = new ImageView(imageAdd);
        icon.setFitWidth(width);
        icon.setFitHeight(height);
        button.setGraphic(icon);
    }
}
