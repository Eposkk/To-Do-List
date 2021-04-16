package ntnu.team1.Take2;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;

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
        taskNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number of tasks") );
    }

    private void updateList(){
        registerWrapper = App.getCategoryWrapper();
        TableView.setItems(registerWrapper);
    }

    public ObservableList<Category> getRegisterWrapper() {
        return registerWrapper;
    }

}
