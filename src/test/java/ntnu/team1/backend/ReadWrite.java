package ntnu.team1.backend;

import ntnu.team1.backend.persistence.Read;
import ntnu.team1.backend.persistence.Write;
import ntnu.team1.backend.objects.Category;
import ntnu.team1.backend.objects.Task;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ReadWrite {
    private HashMap<Integer, Category> addCategoryData(){
        HashMap<Integer,Category> test=new HashMap<>();
        Category cat1 = new Category(1, Color.BLUE,"Skole");
        Category cat2 = new Category(2, Color.GREEN,"Arbeid");
        test.put(cat1.getID(), cat1);
        test.put(cat2.getID(), cat2);
        return test;
    }

    private ArrayList<Task> addTaskData(){
        ArrayList<Task> test=new ArrayList<>();
        Task task1= new Task(1, "Grave","Grave i hagen", LocalDate.now(),LocalDate.now(),2,1);
        Task task2= new Task(2,"ikk","Ikke Grave i hagen", LocalDate.now(),LocalDate.now(),2,2);
        test.add(task1);
        test.add(task2);
        return test;
    }

    private MainRegister addRegister(){
        MainRegister register = new MainRegister();
        register.setCategories(addCategoryData());
        register.setTasks(addTaskData());
        return register;
    }

   @Test
    void writeAndReadPositive() {
        MainRegister register = addRegister();
        Write write = new Write(register);
        write.writeRegister();

        Read read = new Read("data/mainRegister.ser");
        MainRegister readRegister = read.readRegister();
        for(int i=0; i< register.getAllTasks().size();i++){
            assert (register.getAllTasks().get(i).equals(readRegister.getAllTasks().get(i)));
        }
       for(int i=1; i< register.getCategories().size()+1;i++){
           assert (register.getCategories().get(i).equals(readRegister.getCategories().get(i)));
       }
    }

    @Test
    public void writeAndReadNullPointerException() {
        Read read = new Read("data/eqw");
        assertThrows(NullPointerException.class, read::readRegister);
    }
}