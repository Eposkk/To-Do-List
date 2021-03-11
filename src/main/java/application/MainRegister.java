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
    private ArrayList<MainEvent> events;

    public MainRegister(){
        categories = new HashMap<>();
        events = new ArrayList<>();
    }
    public Category getCategory(int Id){
        return categories.get(Id);
    }
    public Event getEvent(int Id) {
        for (MainEvent event : events) {
            if (event.getID() == Id) {
                return event;
            }
        }
        return null;
    }
    public boolean addEvent(MainEvent newMainEvent){
        if(events.contains(newMainEvent)){
            return false;
        }else{
            events.add(newMainEvent);
            return true;
        }
    }
    public void setCategoryColor(int Id, Color color){
        categories.get(Id).setColor(color);
    }
    public boolean removeEvent(int mainEventId){
        for(MainEvent event : events){
            if(event.getID() == mainEventId){
                return events.remove(event);
            }
        }
        return false;
    }
    public boolean setEventCategory(int eventId, int newCategoryId){
        for(MainEvent event: events){
            if(eventId == event.getID()){
                event.setCategoryId(newCategoryId);
                return true;
            }
        }
        return false;
    }
    public boolean removeCategory(int Id){
        if(categories.containsKey(Id)){
            categories.remove(Id);
            return true;
        }else{
            return false;
        }
    }

    //TODO sortering
    private void sortByPriority(){

    }
    private void sortByCategory(int Id){

    }
    public ArrayList<MainEvent> getAllEventsFromCategory(int Id){
        ArrayList<MainEvent> eventsByCategory = new ArrayList<>();
        for(MainEvent event: events){
            if(event.getCategoryId() == Id){
                eventsByCategory.add(event);
            }
        }
        return eventsByCategory;
    }
    public ArrayList<MainEvent> getAllEvents(){
        ArrayList<MainEvent> allMainEvents = new ArrayList<MainEvent>();
        allMainEvents.addAll(events);
        return allMainEvents;
    }

    //TODO toString()

    public void Save(){

    }
    public HashMap<Integer, Category> readCategory(){

    }
    public ArrayList<Event> readEvent(){

    }
}
