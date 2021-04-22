package ntnu.team1.mainApplication.category;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.exceptions.RemoveException;
import ntnu.team1.application.task.Category;
import ntnu.team1.mainApplication.App;
import ntnu.team1.mainApplication.registerModifiers;
import ntnu.team1.mainApplication.task.TaskDialog;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class CategoryListController {


    @FXML
    private TableView<Category> tableView;

    @FXML
    public TableColumn<Category, String> nameColumn;
    @FXML
    public TableColumn<Color, Color> colorColumn;
    @FXML
    public TableColumn<Category, Integer> taskNumberColumn;

    public void initialize(){
        columFactory();
        updateList();
    }

    private void columFactory(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
    }


    @FXML
    public void addNewCategory(){
        registerModifiers.addNewCategory();
        updateList();
    }

    @FXML
    private void removeCategory() throws RemoveException {
        MainRegister result = App.getRegister();
        result.removeCategory(tableView.getSelectionModel().getSelectedItem().getID());
        App.setRegister(result);
        updateList();
    }

    @FXML
    public void editCategory(){
        registerModifiers.editCategory(tableView.getSelectionModel().getSelectedItem());
       updateList();
    }

    private void updateList(){
        ObservableList<Category> list = FXCollections.observableList(new ArrayList<>(App.getRegister().getCategories().values()));
        tableView.setItems(list);
    }
}
