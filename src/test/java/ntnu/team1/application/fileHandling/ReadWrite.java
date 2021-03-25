package ntnu.team1.application.fileHandling;

import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ReadWrite {
    private HashMap<Integer, Category> addCategoryData(){
        HashMap<Integer,Category> test=new HashMap<>();
        Category cat1 = new Category(1, Color.BLUE,"Skole");
        Category cat2 = new Category(2, Color.GREEN,"Arbeid");
        test.put(cat1.getID(), cat1);
        test.put(cat2.getID(), cat2);
        return test;
    }

    private ArrayList<MainTask> addTaskData(){
        ArrayList<MainTask> test=new ArrayList<>();
        MainTask task1= new MainTask(1, LocalDate.now(),LocalDate.now(), "Grave","Grave i hagen",2,1);
        MainTask task2= new MainTask(2, LocalDate.now(),LocalDate.now(),"ikk","Ikke Grave i hagen",2,2);
        test.add(task1);
        test.add(task2);
        return test;
    }

    @Test
    void writeAndReadPositive() {
        HashMap<Integer,Category> data=addCategoryData();
        ArrayList<MainTask> tasks = addTaskData();
        Write write = new Write(data,tasks);
        write.writeCategories();
        write.writeTasks();
        Read read = new Read("data/categories.ser","data/tasks.ser");
        HashMap<Integer,Category> dataRead = read.readCategory();
        ArrayList<MainTask> tasksRead= read.readTasks();
        assert(data.equals(dataRead));
        assert (tasksRead.equals(tasks));
    }

    @Test
    void writeAndReadNullPointerException() {
        Read read = new Read("data/categor.ser","data/tass.ser");
        assertThrows(NullPointerException.class, () -> { read.readTasks();});
        assertThrows(NullPointerException.class, () -> { read.readCategory();});
    }
}