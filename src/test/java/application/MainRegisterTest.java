package application;

import application.task.MainTask;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainRegisterTest {

    @Test
    void addMainTask() {
    }

    @Test
    void addCategory() {
    }

    @Test
    void removeTask() {
        MainRegister register = new MainRegister();

        register.addCategory("Kategori_1", Color.pink);
        register.addCategory("Kategori_2", Color.blue);
        register.addCategory("Kategori_3", Color.red);
        register.addCategory("Kategori_4", Color.green);

        MainTask mainTask = new MainTask(1,LocalDate.now(), "Name", "Lorem Ipsum", 1, 1);


        for(int i = 0; i<=10;i++) {
            LocalDate date = null;
            String name = "task " + i;
            String description = "Lorem Ipsum";
            Random random = new Random();

            register.addMainTask(date, name, description, random.nextInt(3), random.nextInt(3));
        }
            register.removeTask(1);


        }

    }

    @Test
    void setTaskCategory() {
    }

    @Test
    void removeCategory() {
    }

    @Test
    void sortByPriority() {
    }

    @Test
    void sortByCategory() {
    }
}