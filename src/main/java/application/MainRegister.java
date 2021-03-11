package application;

import application.event.Event;
import application.event.Category;
import application.event.MainEvent;

import javax.xml.catalog.Catalog;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class MainRegister {
    private HashMap<Integer, Category> categories;
    private ArrayList<Event> events;

    public MainRegister(){
        categories = new HashMap<Integer, Category>();
        events = new ArrayList<Event>();
    }
    public Category getCategory(int key){

    }
    public Event getEvent(String eventName){

    }
    public boolean addEvent(MainEvent newMainEvent){

    }
    public boolean setCategoryColor(int Id, Color color){

    }
    public boolean removeEvent(MainEvent mainEvent){

    }
    public boolean setEventCategory(MainEvent mainEvent, int newCategory){

    }
    public boolean removeCategory(int Id){

    }
    private void sortByPriority(){

    }
    private void sortByCategory(int Id){

    }
    public ArrayList<Event> getAllEventsFromCategory(int Id){

    }
    public ArrayList<Event> getAllEvents(){

    }

    @Override
    public String toString() {

    }
    public void Save(){

    }
    public HashMap<Integer, Category> readCategory(){

    }
    public ArrayList<Event> readEvent(){

    }
}
