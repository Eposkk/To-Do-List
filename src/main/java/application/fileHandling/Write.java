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
    private final ArrayList<MainTask> events;

    public Write(HashMap<Integer,Category> categories,ArrayList<MainTask> events){
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
