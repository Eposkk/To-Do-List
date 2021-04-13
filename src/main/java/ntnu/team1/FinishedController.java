package ntnu.team1;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.team1.application.MainRegister;
import ntnu.team1.application.fileHandling.Read;
import ntnu.team1.application.task.MainTask;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FinishedController {

    public VBox showTasks;
    public VBox allCategoriesVBox;
    private MainRegister register = new MainRegister();

    /**
     *
     */
        @FXML
        public void initialize () throws IOException {
            Read reader = new Read("data/categories.ser", "data/tasks.ser");
            register.setCategories(reader.readCategory());
            register.setTasks(reader.readTasks());
            App.setReg(register);
            updateTasks();

            for (int i = 0; i < register.getCategories().size(); i++) {
                Label l = new Label(register.getCategories().get(i).getName());
                l.setId("labelCategoryBox");
                HBox c = new HBox(l);
                c.setId("categoryBox");
                allCategoriesVBox.getChildren().add(c);
                allCategoriesVBox.getChildren().add(new Separator());
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

        /**
         * A method that switch scene to main
         * @throws IOException
         */

        public void switchToMain () throws IOException {
            App.setRootWithSave("main", register);
        }


        /**
         * A method that delete all finished tasks
         */
        public void deleteAllFinished() {
            ArrayList<MainTask> task = (ArrayList<MainTask>) register.getAllTasks().stream().filter(i -> i.isDone()).collect(Collectors.toList());
            // register.setTasks(task);
            for (MainTask t : task) {
                register.removeMainTask(t.getID());
                updateTasks();
            }
        }

        private void updateTasks () {
            showTasks.getChildren().clear();
            showTasks.setPrefWidth(969);
            for (MainTask t : register.getAllTasks()) {
                if (t.isDone()) {
                    HBox hBox = new HBox();
                    hBox.setId("taskBox");

                    AnchorPane anchorPane = new AnchorPane();
                    ImageView imageView = new ImageView();

                    CheckBox checkBox = new CheckBox();
                    checkBox.setId(String.valueOf(t.getID()));
                    checkBox.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                        t.setDone(false);
                        updateTasks();
                    });

                    Label taskName = new Label(t.getName());

                    Label startDate = new Label(String.valueOf(t.getStartDate()));

                    Label endDate = new Label(String.valueOf(t.getEndDate()));

                    Label description = new Label(t.getDescription());

                    Label priority = new Label(String.valueOf(t.getPriority()));

                    Button delete = new Button("Delete");
                    delete.setOnAction(event -> {
                                register.removeMainTask(t.getID());
                                updateTasks();
                            }
                    );

                    Separator separator = new Separator();

                    hBox.getChildren().add(checkBox);
                    hBox.getChildren().add(taskName);
                    hBox.getChildren().add(description);
                    hBox.getChildren().add(startDate);
                    hBox.getChildren().add(endDate);
                    hBox.getChildren().add(priority);
                    if (t.hasCategory()) {
                        Label category = new Label(register.getCategory(t.getCategoryId()).getName());
                        hBox.getChildren().add(category);
                    }
                    hBox.getChildren().add(delete);

                    separator.setOrientation(Orientation.HORIZONTAL);
                    separator.setPrefWidth(900);
                    separator.setLayoutX(95);
                    separator.setLayoutY(108);
                    anchorPane.getChildren().add(separator);
                    anchorPane.setPrefHeight(3);
                    AnchorPane.setLeftAnchor(separator, 80.0);
                    AnchorPane.setBottomAnchor(separator, 0.0);
                    AnchorPane.setTopAnchor(separator, 0.0);


                    checkBox.setPrefWidth(80);
                    checkBox.setPrefHeight(54);
                    checkBox.setPadding(new Insets(0, 0, 0, 30));
                    checkBox.setAlignment(Pos.CENTER);

                    imageView.setFitHeight(29);
                    imageView.setFitWidth(30);

                    showTasks.getChildren().add(hBox);
                    showTasks.getChildren().add(anchorPane);
                }
            }
        }
    }
