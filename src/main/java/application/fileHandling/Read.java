package application.fileHandling;

import application.event.Category;

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

    public ArrayList<MainEvent> readEvents(){
        ArrayList<MainEvent> events=null;
        try{
            FileInputStream fileInputStream = new FileInputStream("data/events.ser");
            ObjectInputStream in= new ObjectInputStream(fileInputStream);
            events=(ArrayList<MainEvent>) in.readObject();
            fileInputStream.close();
            in.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        return events;
    }
}
