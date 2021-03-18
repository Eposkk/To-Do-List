package application.fileHandling;

import application.task.MainTask;
import application.task.Category;

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
        try{
            FileOutputStream fileOutCategories= new FileOutputStream("data/categories.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutCategories);
            out.writeObject(categories);
            out.close();
            fileOutCategories.close();
        }catch (IOException i){
            System.out.println(i.getMessage());
        }

    }

    public void writeTasks(){
        try{
            FileOutputStream fileOutTasks= new FileOutputStream("data/tasks.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutTasks);
            out.writeObject(tasks);
            out.close();
            fileOutTasks.close();
        }catch (IOException i){
            System.out.println(i.getMessage());
        }

    }

}
