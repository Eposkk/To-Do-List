package ntnu.team1.application.fileHandling;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;

public class Read {
    String pathCategory;
    String pathEvent;
    public Read(String pathCategory, String pathEvent){
        this.pathCategory=pathCategory;
        this.pathEvent=pathEvent;
    }

    public HashMap<Integer, Category> readCategory(){
        HashMap<Integer, Category> categories=null;
        FileInputStream fileInputStream = null;
        ObjectInputStream in = null;
        try{
            fileInputStream = new FileInputStream(pathCategory);
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
            fileInputStream = new FileInputStream(pathEvent);
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

