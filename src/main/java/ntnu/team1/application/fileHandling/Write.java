package ntnu.team1.application.fileHandling;


import ntnu.team1.application.task.MainTask;
import ntnu.team1.application.task.Category;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Write {
    private final HashMap<Integer,Category> categories;
    private final ArrayList<MainTask> tasks;

    public Write(HashMap<Integer,Category> categories,ArrayList<MainTask> tasks){
        this.categories=categories;
        this.tasks = tasks;
    }

    public void writeCategories(){
        FileOutputStream fileOutCategories = null;
        ObjectOutputStream out = null;
        try{
            fileOutCategories = new FileOutputStream("data/categories.ser");
            out = new ObjectOutputStream(fileOutCategories);
            out.writeObject(categories);
        }catch (IOException i){
            System.out.println(i.getMessage());
        }
        finally { //The files will close even tough an exception will be caught when writing to task files.
            try{
                out.close();
                fileOutCategories.close();
            }
            catch (IOException i){
                System.out.println(i.getMessage());
            }
        }
    }

    public void writeTasks(){
        FileOutputStream fileOutTasks = null;
        ObjectOutputStream out = null;
        try{
            fileOutTasks = new FileOutputStream("data/tasks.ser");
            out = new ObjectOutputStream(fileOutTasks);
            out.writeObject(tasks);
        }catch (IOException i){
            System.out.println(i.getMessage());
        }
        finally { //The files will close even tough an exception will be caught when writing to task files.
            try{
                out.close();
                fileOutTasks.close();
            }
            catch (IOException i){
                System.out.println(i.getMessage());

            }
        }

    }

}

