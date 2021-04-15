package ntnu.team1.MVP;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Read;

import java.io.IOException;


public class CategoryController {

    @FXML
    public TextArea categoryName;
    public ColorPicker categoryColorPicker;
    @FXML
    VBox allCategoriesVBox;
    @FXML
    VBox originalCategories;
    @FXML
    HBox upcomingCategoryHBox;
    @FXML
    HBox upcomingCategoryHBox1;

    MainRegister register = new MainRegister();

    @FXML
    public void initialize() throws IOException {
        Read reader = new Read("data/categories.ser", "data/tasks.ser");
        register.setCategories(reader.readCategory());
        register.setTasks(reader.readTasks());
        App.setReg(register);
        try {
            updateCategories();
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }

    public void submitCategory(){
        System.out.println(register.addCategory(categoryName.getText(),categoryColorPicker.getValue()));
        updateCategories();
    }

    public void updateCategories(){
        VBox allOriginalCategories = originalCategories;
        allCategoriesVBox.getChildren().clear();
        allCategoriesVBox.getChildren().add(allOriginalCategories);
        for (int i = 0; i < register.getCategories().size(); i++) {
            Label l = new Label(register.getCategories().get(i).getName());
            l.setId("labelCategoryBox");
            HBox c = new HBox(l);
            c.setId("categoryBox");
            try {
                allCategoriesVBox.getChildren().add(c);
                allCategoriesVBox.getChildren().add(new Separator());
            }catch (NullPointerException n){
                System.out.println(n.getMessage());
            }
        }
        Label l = new Label("Add new category");
        l.setId("labelCategoryBox");
        HBox c = new HBox(l);
        c.setId("categoryBox");
        c.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        try {
                            App.setRootWithSave("category", register);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        allCategoriesVBox.getChildren().add(c);
    }

    public void switchToToday() throws IOException {
        App.setRootWithSave("main",register);
    }

    public void switchToFinished() throws IOException {
        App.setRootWithSave("finished",register);
    }

}
