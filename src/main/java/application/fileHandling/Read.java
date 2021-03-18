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
        FileInputStream fileInputStream = null;
        ObjectInputStream in = null;
        try{
            fileInputStream = new FileInputStream("data/categories.ser");
            in = new ObjectInputStream(fileInputStream);
            categories=(HashMap<Integer, Category>) in.readObject();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        finally { //The files will close even tough an exception will be caught when reading from category files.
            try{
                fileInputStream.close();
                in.close();
            }
            catch (IOException i){
                System.out.println(i.getMessage());
            }
        }
        return categories;
    }

    public ArrayList<MainTask> readTasks(){
        ArrayList<MainTask> tasks = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream in = null;
        try{
            fileInputStream = new FileInputStream("data/tasks.ser");
            in= new ObjectInputStream(fileInputStream);
            tasks=(ArrayList<MainTask>) in.readObject();


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        finally { //The files will close even tough an exception will be caught when reading from task files.
            try{
                fileInputStream.close();
                in.close();
            }
            catch (IOException i){
                System.out.println(i.getMessage());
            }
        }
        return tasks;
    }
}
