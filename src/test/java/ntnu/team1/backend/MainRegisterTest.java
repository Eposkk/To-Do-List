package ntnu.team1.backend;

import ntnu.team1.backend.exceptions.RemoveException;
import ntnu.team1.backend.objects.Category;
import ntnu.team1.backend.objects.Task;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MainRegisterTest {

    @Nested
    public class CategoryMethodsTests{

        @Nested
        public class addCategoryTest{
            @Test
            public void addCategoryTestPositive() {
                MainRegister register = new MainRegister();
                assertTrue(register.addCategory("test", Color.PINK));
            }
            @Test
            public void addCategoryTestNegative() {
                MainRegister register = new MainRegister();
                assertThrows(NullPointerException.class, ()-> register.addCategory("", Color.RED));
            }
        }

        @Nested
        public class removeCategoryTest{
            @Test
            public void removeCategoryTestPositive(){
                MainRegister register = new MainRegister();
                register.addCategory("test", Color.PINK);
                try{
                    register.removeCategory(0);
                }catch(RemoveException e){
                    assertNull(e);
                }
                assert(register.getCategories().size() == 1);
            }

            @Test
            public void removeCategoryTestNegative(){
                MainRegister register = new MainRegister();
                assertThrows(RemoveException.class, ()-> register.removeCategory(0));
            }
        }
        @Nested
        public class editCategoryTest{
            @Test
            void editCategoryTestPositive() {
                MainRegister register = new MainRegister();
                register.addCategory("test", Color.PINK);
                Category edited = new Category(0, Color.RED, "edited");
                register.editCategory(edited);
                assertEquals(edited, register.getCategory(0));
            }
            @Test
            void editCategoryTestNegative() {
                MainRegister register = new MainRegister();
                assertThrows(NullPointerException.class, ()-> register.editCategory(register.getCategory(0)));
            }
        }
    }

    @Nested
    public class TaskTest {

        @Nested
        public class editTaskTest{
            @Test
            void editTaskTestPositive() {
                MainRegister register = new MainRegister();
                register.addTask(null, null, "test", "description", 1, -1);
                register.editTask(0,LocalDate.MIN, LocalDate.MAX, "Edited", "This is now edited", 2, 2);
                Task testTask = new Task(0,"Edited", "This is now edited",LocalDate.MIN, LocalDate.MAX,  2, 2);
                assertEquals(testTask, register.getTask(0));
            }
            @Test
            void editTaskTestNegative(){
                MainRegister register = new MainRegister();
                register.addTask(null, null, "test", "description", 1, -1);
                assertThrows(NullPointerException.class, ()-> register.editTask(0,LocalDate.MIN, LocalDate.MAX, "", "This is now edited", 2, 2));
            }
        }


        @Nested
        public class getTask {
            @Test
            public void getTaskTestPositive(){
                MainRegister register = new MainRegister();
                register.addTask(null, null, "test", "description", 1, -1);
                assertNotNull(register.getTask(0));
            }

            @Test
            public void getTaskTestNegative(){
                MainRegister register = new MainRegister();
                register.addTask(null, null, "test", "description", 1, -1);
                assertThrows(IllegalArgumentException.class, () -> register.getTask(1));
            }
        }

        @Nested
        public class addTask {
            @Test
            public void addTaskTestPositive() {
                MainRegister register = new MainRegister();
                register.addTask(LocalDate.now(), LocalDate.now(),"Task_1",
                        "Lorem Ipsum", 0,0);

                assertNotNull(register.getTask(0));
            }

            @Test
            public void addTaskTestNegative() {
                MainRegister register = new MainRegister();
                assertThrows(NullPointerException.class, ()-> register.addTask(null, null,
                        null, "Lorem Ipsum",1 ,1));
            }
        }

        @Nested
        public class removeTask {

            @Test
            public void removeTaskTestPositive() {
                MainRegister register = new MainRegister();

                register.addCategory("Category_1", Color.PINK);
                register.addCategory("Category_2", Color.BLUE);
                register.addCategory("Category_3", Color.RED);
                register.addCategory("Category_4", Color.GREEN);

                for(int i = 0; i<=10;i++) {
                    String name = "task " + i;
                    String description = "Lorem Ipsum";
                    Random random = new Random();
                    register.addTask(null, null, name, description, random.nextInt(3), random.nextInt(3));
                }
                assertNotNull(register.getTask(1));

                try{
                    register.removeTask(1);
                }catch (IllegalArgumentException e){
                    assertNull(e);
                }

                assertThrows(IllegalArgumentException.class, () -> register.getTask(1));
            }
            @Test
            public void removeTaskTestNegative(){
                MainRegister register = new MainRegister();

                assertThrows(IllegalArgumentException.class, () -> register.removeTask(1));
            }
        }
    }
}