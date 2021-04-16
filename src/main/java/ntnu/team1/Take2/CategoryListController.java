package ntnu.team1.Take2;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ntnu.team1.application.task.Category;

import java.io.IOException;

public class CategoryListController {


    public javafx.scene.control.TableView TableView;
    public TableColumn nameColumn;
    public TableColumn colorColumn;
    public TableColumn taskNumberColumn;

    private ObservableList<Category> registerWrapper = App.getCategoryWrapper();

    public void initialize(){
        columFactory();
        updateList();
    }

    private void columFactory(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
    }

    private void updateList(){
        registerWrapper = App.getCategoryWrapper();
        TableView.setItems(registerWrapper);
    }

    public ObservableList<Category> getRegisterWrapper() {
        return registerWrapper;
    }

    public void addNewCategory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "newcategory.fxml"));
        Parent parent = fxmlLoader.load();
        addCategoryDialogController dialogController = new addCategoryDialogController();
        CategoryListController toDoController = new CategoryListController();
        dialogController.setAppCategoryObservableList(toDoController.getRegisterWrapper());

        Scene scene = new Scene(parent, 800, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
