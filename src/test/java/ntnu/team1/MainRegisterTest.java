package ntnu.team1;

import ntnu.team1.application.exceptions.RemoveException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.scene.paint.Color;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainRegisterTest {

    @Nested
    class Category{

        @Nested
        class addCategory{
            @Test
            void addCategoryPositive() {
                MainRegister register = new MainRegister();
                assertTrue(register.addCategory("test", Color.PINK));
            }
            @Test
            void addCategoryNegative() {
                MainRegister register = new MainRegister();
                register.addCategory("test", Color.PINK);
                assertFalse(register.addCategory("test", Color.PINK));
            }
        }

        @Nested
        class removeCategory{
            @Test
            void removeCategoryPositive(){
                MainRegister register = new MainRegister();
                register.addCategory("test", Color.PINK);
                try{
                    register.removeCategory(0);
                }catch(RemoveException e){
                    assertNull(e);
                }
                assert(register.getCategories().size() == 0);
            }

            @Test
            void removeCategoryNegative(){
                MainRegister register = new MainRegister();
                assertThrows(RemoveException.class, ()-> register.removeCategory(0));
            }
        }

        @Nested
        class setCategoryColor{

            @Test
            void setCategoryColorPositive(){
                MainRegister register = new MainRegister();
                assertTrue(register.addCategory("test", Color.PINK));

                register.setCategoryColor(0,Color.GREEN);
                assertEquals(register.getCategory(0).getColor(), Color.GREEN);
            }

            @Test
            void setCategoryColorNegative(){
                MainRegister register = new MainRegister();
                assertTrue(register.addCategory("test", Color.PINK));

                assertThrows(IllegalArgumentException.class, () -> register.setCategoryColor(1,Color.GREEN));
            }
        }

    }

    @Nested
    class MainTask{

        @Nested
        class getMainTask{

            //TODO
            // Use better assert
            @Test
            void getMainTaskPositive(){
                MainRegister register = new MainRegister();
                register.addMainTask(null, null, "test", "description", 1, -1);
                assertNotNull(register.getMainTask(0));
            }

            @Test
            void getMainTaskNegative(){
                MainRegister register = new MainRegister();
                register.addMainTask(null, null, "test", "description", 1, -1);
                assertThrows(IllegalArgumentException.class, () -> register.getMainTask(1));
            }
        }

        @Nested
        class addMainTask{
            @Test
            void addMainTaskPositive() {
                MainRegister register = new MainRegister();
                register.addMainTask(LocalDate.now(), LocalDate.now(),"Task_1",
                        "Lorem Ipsum", 0,0);

                assertNotNull(register.getMainTask(0));
            }

            @Test
            void addMainTaskNegative() {
                MainRegister register = new MainRegister();
                assertThrows(NullPointerException.class, ()-> register.addMainTask(null, null,
                        null, "Lorem Ipsum",1 ,1));
            }
        }

        @Nested
        class removeMainTask{

            @Test
            void removeMainTaskPositive() {
                MainRegister register = new MainRegister();

                register.addCategory("Kategori_1", Color.PINK);
                register.addCategory("Kategori_2", Color.BLUE);
                register.addCategory("Kategori_3", Color.RED);
                register.addCategory("Kategori_4", Color.GREEN);

                for(int i = 0; i<=10;i++) {
                    String name = "task " + i;
                    String description = "Lorem Ipsum";
                    Random random = new Random();
                    register.addMainTask(null, null, name, description, random.nextInt(3), random.nextInt(3));
                }
                assertNotNull(register.getMainTask(1));

                try{
                    register.removeMainTask(1);
                }catch (IllegalArgumentException e){
                    assertNull(e);
                }

                assertThrows(IllegalArgumentException.class, () -> register.getMainTask(1));
            }
            @Test
            void removeMainTaskNegative(){
                MainRegister register = new MainRegister();

                assertThrows(IllegalArgumentException.class, () -> register.removeMainTask(1));
            }
        }

        @Nested
        class changePriority{
            @Test
            void changePriorityPositive(){
                MainRegister register = new MainRegister();
                register.addMainTask(null, null, "test", "description", 1, -1);

                assertEquals(1, register.getMainTask(0).getPriority());
                register.changePriorityMainTask(0,2);
                assertEquals(2, register.getMainTask(0).getPriority());
            }
        }

        @Nested
        class getAllTasksFromCategory{

            @Test
            void getAllTasksFromCategoryPositive(){
                MainRegister register = new MainRegister();
                String name = "task";
                String description = "Lorem Ipsum";
                Random random = new Random();

                register.addCategory("Kategori_1", Color.PINK);
                register.addCategory("Kategori_2", Color.BLUE);
                register.addCategory("Kategori_3", Color.RED);
                register.addCategory("Kategori_4", Color.GREEN);

                register.addMainTask(null, null, name, description, random.nextInt(3), 2);
                register.addMainTask(null, null, name, description, random.nextInt(3), 2);
                register.addMainTask(null, null, name, description, random.nextInt(3), 0);
                register.addMainTask(null, null, name, description, random.nextInt(3), 1);

                assert(register.getAllTasksFromCategory(2).size() == 2);
            }
            @Test
            void getAllTasksFromCategoryNegative(){
                MainRegister register = new MainRegister();
                assertThrows(IllegalArgumentException.class, () -> register.getAllTasksFromCategory(1));
            }
        }

        @Nested
        class setMainTaskCategory{
            @Test
            void setMainTaskCategoryPositive(){

                try{
                    MainRegister register = new MainRegister();
                    register.addMainTask(null, null, "test", "description", 1, -1);
                    register.addCategory("Name", Color.PINK);

                    register.setMainTaskCategory(0, 0);

                    assert(register.getMainTask(0).getCategoryId() == 0);

                }catch(IllegalArgumentException e){
                    assertNull(e.getMessage());

                }
            }

            @Test
            void setMainTaskCategoryNegative(){

                try{
                    MainRegister register = new MainRegister();
                    register.addMainTask(null, null, "test", "description", 1, -1);

                    register.setMainTaskCategory(0, 1);

                }catch(IllegalArgumentException e){
                    assertEquals(e.getMessage(), "Category does not exist");
                }
            }
        }

        @Nested
        class changeDescriptionMainTask {

            @Test
            void changeDescriptionMainTaskPositive() {
                try {

                    MainRegister register = new MainRegister();
                    register.addMainTask(null, null, "test", "description", 1, -1);
                    register.changeDescriptionMainTask(0, "New description");
                    assertEquals(register.getMainTask(0).getDescription(), "New description");

                } catch (IllegalArgumentException e) {
                    assertNull(e.getMessage());
                }
            }

            @Test
            void changeDescriptionMainTaskNegative() {
                try {
                    MainRegister register = new MainRegister();
                    register.addMainTask(null, null, "test", "description", 1, -1);
                    register.changeDescriptionMainTask(1, "New description");
                } catch (IllegalArgumentException e) {
                    assertEquals(e.getMessage(), "No task found with the suggested Id.");
                }
            }
        }
    }


    @Test
    void sortByPriority() {
        MainRegister register = new MainRegister();
        register.addCategory("Kategori_1", Color.PINK);
        register.addCategory("Kategori_2", Color.BLUE);
        register.addCategory("Kategori_3", Color.RED);
        register.addCategory("Kategori_4", Color.GREEN);
        int n=1000;
        for(int i = 0; i<=n;i++) {
            String name = "task " + i;
            String description = "Lorem Ipsum";
            Random random = new Random();
            register.addMainTask(null, null, name, description, random.nextInt(3), random.nextInt(3));
        }

        register.sortByPriority();

        for(int i = 0; i<n-1;i++){
            assert (register.getAllTasks().get(i).getPriority()<=register.getAllTasks().get(i+1).getPriority());
        }
    }

    @Test
    void sortByCategory() {
        MainRegister register = new MainRegister();
        register.addCategory("Kategori_1", Color.PINK);
        register.addCategory("Kategori_2", Color.BLUE);
        register.addCategory("Kategori_3", Color.RED);
        register.addCategory("Kategori_4", Color.GREEN);
        int n=1000;
        for(int i = 0; i<=n;i++) {
            String name = "task " + i;
            String description = "Lorem Ipsum";
            Random random = new Random();
            register.addMainTask(null, null, name, description, random.nextInt(3), random.nextInt(3));
        }

        register.sortByCategory();

        for(int i = 0; i<n-1;i++){
            assert (register.getAllTasks().get(i).getCategoryId()<=register.getAllTasks().get(i+1).getCategoryId());
        }
    }
}