package application.fileHandling;

import application.task.Category;
import application.task.MainTask;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class will read from serialized objects and returns them deserialized
 */

public class Read {

    public Read(){
    }

    public HashMap<Integer, Category> readCategory(){
        HashMap<Integer, Category> categories=null;
        try{
            FileInputStream fileInputStream = new FileInputStream("data/categories.ser");
            ObjectInputStream in= new ObjectInputStream(fileInputStream);
            categories=(HashMap<Integer, Category>) in.readObject();
            fileInputStream.close();
            in.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        return categories;
    }

    public ArrayList<MainTask> readTasks(){
        ArrayList<MainTask> tasks=null;
        try{
            FileInputStream fileInputStream = new FileInputStream("data/tasks.ser");
            ObjectInputStream in= new ObjectInputStream(fileInputStream);
            tasks=(ArrayList<MainTask>) in.readObject();
            fileInputStream.close();
            in.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        return tasks;
    }
}
