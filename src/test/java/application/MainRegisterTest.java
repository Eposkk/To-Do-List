package application;

import application.exceptions.RemoveException;
import application.task.Category;
import application.task.MainTask;
import com.sun.tools.javac.Main;
import jdk.jfr.Name;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainRegisterTest {



    @Nested
    class addCategory{
        @Test
        void addCategoryPositive() {
            MainRegister register = new MainRegister();
            assertTrue(register.addCategory("test", Color.pink));
        }
        @Test
        void addCategoryNegative() {
            MainRegister register = new MainRegister();
            register.addCategory("test", Color.pink);
            assertFalse(register.addCategory("test", Color.pink));
        }
    }


    @Nested
    class MainTask{

        @Nested
        class addMainTask{
            @Test
            void addMainTaskPositive() {
                MainRegister register = new MainRegister();
                register.addMainTask(LocalDate.now(), LocalDate.now(),"Task_1",
                        "Lorem Ipsum", 0,0);

                assertNotNull(register.getTask(0));
            }

            void addMainTaskNegative() {
                MainRegister register = new MainRegister();
                assertThrows(NullPointerException.class, ()-> register.addMainTask(null, null,
                        null, "Lorem Ipsum",1 ,1));
            }
        }
        @Nested
        class removeMainTask{

            @Test
            void removeMainTaskPositive() throws RemoveException {
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
                    register.addMainTask(date, date, name, description, random.nextInt(3), random.nextInt(3));
                }
                assertNotNull(register.getTask(1));

                System.out.println(register.getAllTask());

                try{
                    register.removeMainTask(1);
                }catch (RemoveException e){
                    assertNull(e);
                }

                assertNull(register.getTask(1));
            }
            @Test
            void removeMainTaskNegative()  {
                MainRegister register = new MainRegister();

                assertThrows(RemoveException.class, () -> register.removeMainTask(1));
            }
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