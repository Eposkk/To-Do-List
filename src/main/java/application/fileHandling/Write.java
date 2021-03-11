package application.fileHandling;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Write {
    private HashMap<Integer,Category> categories;
    private ArrayList<MainEvent> events;

    public Write(HashMap<Int,Category> categories,ArrayList<MainEvent> events){
        this.categories=categories;
        this.events=events;
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

    public void writeEvents(){
        try{
            FileOutputStream fileOutEvents= new FileOutputStream("data/events.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutEvents);
            out.writeObject(events);
            out.close();
            fileOutEvents.close();
        }catch (IOException i){
            System.out.println(i.getMessage());
        }

    }

}
