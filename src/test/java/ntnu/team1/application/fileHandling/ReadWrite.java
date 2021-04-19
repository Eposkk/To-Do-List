package ntnu.team1.application.fileHandling;

import ntnu.team1.application.MainRegister;
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
        MainTask task1= new MainTask(1, "Grave","Grave i hagen", LocalDate.now(),LocalDate.now(),2,1);
        MainTask task2= new MainTask(2,"ikk","Ikke Grave i hagen", LocalDate.now(),LocalDate.now(),2,2);
        test.add(task1);
        test.add(task2);
        return test;
    }
    
    //TODO: Remake tests

   /* @Test
    void writeAndReadPositive() {
        HashMap<Integer,Category> data=addCategoryData();
        ArrayList<MainTask> tasks = addTaskData();
        MainRegister register = new MainRegister();
        Write write = new Write(register);
        write.writeRegister();

        Read read = new Read("data/register.ser");
        assert(data.equals(dataRead));
        assert (tasksRead.equals(tasks));
    }

    @Test
    void writeAndReadNullPointerException() {
        Read read = new Read("data/categor.ser","data/tass.ser","data/eqw");
        assertThrows(NullPointerException.class, () -> { read.readTasks();});
        assertThrows(NullPointerException.class, () -> { read.readCategory();});
    }*/
}