package ntnu.team1.Take2;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.exceptions.RemoveException;
import ntnu.team1.application.task.Category;

import java.io.IOException;

public class CategoryListController {


    @FXML
    private TableView<Category> tableView;

    @FXML
    public TableColumn<Category, String> nameColumn;
    @FXML
    public TableColumn colorColumn;
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
    public void addNewCategory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "newCategory.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 800, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        updateList();
    }

    @FXML
    private void removeCategory() throws RemoveException {
        MainRegister result = App.getRegister();
        result.removeCategory(((Category) tableView.getSelectionModel().getSelectedItem()).getID());
        App.setRegister(result);
        updateList();
    }

    private void updateList(){
        tableView.setItems(App.getCategoryWrapper());
    }
}
