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
    public Category getCategory(int Id){
        return categories.get(Id);
    }
    public Event getEvent(String eventName){

    }
    public boolean addEvent(MainEvent newMainEvent){

    }
    public void setCategoryColor(int Id, Color color){
        categories.get(Id).setColor(color);
    }
    public boolean removeEvent(MainEvent mainEvent){

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

    }
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
