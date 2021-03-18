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
    private int taskIdCount=0;
    private int categoryIdCount=0;

    public MainRegister(){
        categories = new HashMap<>();
        tasks = new ArrayList<>();
    }
    public Category getCategory(int Id){
        return categories.get(Id);
    }

    public Task getTask(int Id) {
        for (MainTask task : tasks) {
            if (task.getID() == Id) {
                return task;
            }
        }
        return null;
    }

    public boolean addMainTask(LocalDate date, String name, String description, int priority, int categoryId){
        MainTask e;
        if(categoryId == -1){
            e = new MainTask(taskIdCount, date, name, description, priority);
        }else{
            e = new MainTask(taskIdCount, date, name, description, priority, categoryId);
        }

        if(tasks.contains(e)){
            return false;
        }else{
            tasks.add(e);
            taskIdCount+=1;
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

    public boolean removeTask(int mainTaskId){
        for(MainTask task : tasks){
            if(task.getID() == mainTaskId){
                return tasks.remove(task);
            }
        }
        return false;
    }

    public boolean setTaskCategory(int taskId, int newCategoryId){
        for(MainTask task: tasks){
            if(taskId == task.getID()){
                task.setCategoryId(newCategoryId);
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

    public ArrayList<MainTask> getAllTaskFromCategory(int CategoryId){
        ArrayList<MainTask> tasksByCategory = new ArrayList<>();
        for(MainTask task: tasks){
            if(task.getCategoryId() == CategoryId){
                tasksByCategory.add(task);
            }
        }
        return tasksByCategory;
    }

    public ArrayList<MainTask> getAllTask(){
        ArrayList<MainTask> allMainTasks = new ArrayList<MainTask>();
        allMainTasks.addAll(tasks);
        return allMainTasks;
    }

    //TODO toString()

    public void Save(){
        //TODO add code
    }
    public HashMap<Integer, Category> readCategory(){
        //TODO add code
        return null;
    }
    public ArrayList<Task> readTask(){
        //TODO add code
        return null;
    }

    @Override
    public String toString() {
        return "MainRegister{" +
                "categories=" + categories +
                ", tasks=" + tasks +
                ", taskIdCount=" + taskIdCount +
                ", categoryIdCount=" + categoryIdCount +
                '}';
    }
}
