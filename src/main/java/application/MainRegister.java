package application;

import application.task.MainTask;
import application.task.Task;
import application.task.Category;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

//TODO add javadoc

public class MainRegister {
    private HashMap<Integer, Category> categories;
    private ArrayList<MainTask> tasks;
    private int eventIdCount=0;
    private int categoryIdCount=0;

    public MainRegister(){
        categories = new HashMap<>();
        tasks = new ArrayList<>();
    }
    public Category getCategory(int Id){
        return categories.get(Id);
    }

    public Task getEvent(int Id) {
        for (MainTask event : tasks) {
            if (event.getID() == Id) {
                return event;
            }
        }
        return null;
    }

    public boolean addMainEvent(LocalDate date, String name, String description, int priority, int categoryId){
        MainTask e;
        if(categoryId == -1){
            e = new MainTask(eventIdCount, date, name, description, priority);
        }else{
            e = new MainTask(eventIdCount, date, name, description, priority, categoryId);
        }

        if(tasks.contains(e)){
            return false;
        }else{
            tasks.add(e);
            eventIdCount+=1;
            return true;
        }
    }

    public boolean addCategory(String name, Color color){
        if (categories.containsValue(new Category(0, color, name))) {
            return false;
        }else{
            categories.put(categoryIdCount, new Category(categoryIdCount, color, name));
            categoryIdCount+=1;
            return true;
        }

    }

    public void setCategoryColor(int Id, Color color){
        categories.get(Id).setColor(color);
    }

    public boolean removeEvent(int mainEventId){
        for(MainTask event : tasks){
            if(event.getID() == mainEventId){
                return tasks.remove(event);
            }
        }
        return false;
    }

    public boolean setEventCategory(int eventId, int newCategoryId){
        for(MainTask event: tasks){
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

    public void sortByPriority(){
        tasks.sort(Comparator.comparingInt(Task::getPriority));
    }

    public void sortByCategory(){
        tasks.sort(Comparator.comparingInt(MainTask::getCategoryId));
    }

    public ArrayList<MainTask> getAllEventsFromCategory(int CategoryId){
        ArrayList<MainTask> eventsByCategory = new ArrayList<>();
        for(MainTask event: tasks){
            if(event.getCategoryId() == CategoryId){
                eventsByCategory.add(event);
            }
        }
        return eventsByCategory;
    }

    public ArrayList<MainTask> getAllEvents(){
        ArrayList<MainTask> allMainEvents = new ArrayList<MainTask>();
        allMainEvents.addAll(tasks);
        return allMainEvents;
    }

    //TODO toString()

    public void Save(){
        //TODO add code
    }
    public HashMap<Integer, Category> readCategory(){
        //TODO add code
        return null;
    }
    public ArrayList<Task> readEvent(){
        //TODO add code
        return null;
    }

    @Override
    public String toString() {
        return "MainRegister{" +
                "categories=" + categories +
                ", events=" + tasks +
                ", eventIdCount=" + eventIdCount +
                ", categoryIdCount=" + categoryIdCount +
                '}';
    }
}
