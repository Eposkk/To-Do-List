package application;

import application.task.MainTask;
import jdk.jfr.Name;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainRegisterTest {

    @Test
    void addMainTask() {
    }


    @Nested
    class addCategory{
        @Test
        void addCategoryPositive() {
            MainRegister register = new MainRegister();
        }
    }


    @Nested
    class removeTask{

        @Test
        void removeTaskPositive() {
            MainRegister register = new MainRegister();

            register.addCategory("Kategori_1", Color.pink);
            register.addCategory("Kategori_2", Color.blue);
            register.addCategory("Kategori_3", Color.red);
            register.addCategory("Kategori_4", Color.green);

            for(int i = 0; i<=10;i++) {
                LocalDate date = null;
                String name = "task " + i;
                String description = "Lorem Ipsum";
                Random random = new Random();
                register.addMainTask(new MainTask(register.getTaskIdCount(),date, date, name, description, random.nextInt(3), random.nextInt(3)));
            }
            assertNotNull(register.getTask(1));

            assertTrue(register.removeTask(1));
        }
        @Test
        void removeTaskNegative() {
            MainRegister register = new MainRegister();
            assertFalse(register.removeTask(1));
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