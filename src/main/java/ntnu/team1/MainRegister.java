package ntnu.team1;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.HashMap;

import ntnu.team1.application.exceptions.RemoveException;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.application.task.Task;

/**
 * This is is the main register of the whole app
 * It keeps track of all categories and events by storing them in a HashMap and an ArrayList
 * The Mainregister also allows for adding tasks and adding categories to the registers
 * It also handles saving and reading from file, it also sorts the registers
 */

public class MainRegister {
    private HashMap<Integer, Category> categories;
    private ArrayList<MainTask> tasks;
    private int taskIdCount=0;
    private int categoryIdCount=0;

    /**
     * Constructor for MainRegister
     * Instantiates categories and tasks
     */

    public MainRegister(){
        categories = new HashMap<>();
        tasks = new ArrayList<>();
    }

    /**
     * Returns a category according to the id
     * @param Id id of the category
     * @return the category associated with the id
     */
    public Category getCategory(int Id){
        return categories.get(Id);
    }

    /**
     * Returns a task according to the id
     * @param Id id of the task
     * @return the task associated with the id
     */

    public Task getTask(int Id) {
        for (MainTask task : tasks) {
            if (task.getID() == Id) {
                return task;
            }
        }
        return null;
    }


    public boolean addMainTask(LocalDate startDate, LocalDate endDate, String name, String description, int priority, int categoryId) throws NullPointerException{
        if(name.equals("")){
            throw new NullPointerException("Name cannot be null");
        }

        MainTask task = new MainTask(taskIdCount,startDate,endDate,name,description,priority,categoryId);
        if(tasks.contains(task)){
            return false;
        }else{
            tasks.add(task);
            taskIdCount+=1;
            return true;
        }
    }

    /**
     * Adds a category to the register
     * @param name The name of the category
     * @param color The color associated with the category
     * @return Returns true if the category was registered, returns false if it failed
     */

    public boolean addCategory(String name, Color color){
        if(name.equals("")){
            throw new NullPointerException("Name cannot be null");
        }
        if (categories.containsValue(new Category(-1,color, name))) {
            return false;
        }else{
            categories.put(categoryIdCount, new Category(categoryIdCount, color, name));
            categoryIdCount+=1;
            return true;
        }
    }

    public HashMap<Integer, Category> getCategories() {
        return categories;
    }

    /**
     * Sets the category color
     * @param Id Id of the category
     * @param color Color you want to set
     */

    public void setCategoryColor(int Id, Color color){
        categories.get(Id).setColor(color);
    }

    /**
     * Removes a task from the register
     * @param mainTaskId Id associated with the category
     * @return Returns true if task was removed, returns false if it failed
     */

    public void removeMainTask(int mainTaskId)throws RemoveException {

        boolean removed = false;

        if(tasks.remove(getTask(mainTaskId))){
            removed = true;
        }else{
            throw new RemoveException("Task with id" + mainTaskId + " doesn not exist in the register");
        }

    }

    /**
     * Sets the taks category
     * @param taskId Id associated with the task you want to set a category for
     * @param newCategoryId The category id you want to set to the task
     * @return Returns true if it changed, returns false if it failed
     */

    public boolean setTaskCategory(int taskId, int newCategoryId){
        for(MainTask task: tasks){
            if(taskId == task.getID()){
                task.setCategoryId(newCategoryId);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a category from the register
     * @param Id Id associated with the category
     * @return Returns true if category was removed, returns false if it failed
     */

    public boolean removeCategory(int Id){
        if(categories.containsKey(Id)){
            categories.remove(Id);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Sorts by priority
     */

    public void sortByPriority(){
        tasks.sort(Comparator.comparingInt(Task::getPriority));
    }

    /**
     * Sorts by category
     */

    public void sortByCategory(){
        tasks.sort(Comparator.comparingInt(MainTask::getCategoryId));
    }

    /**
     * Gets all task from a given category
     * @param CategoryId Id Associated with category
     * @return Returns an Arraylist with all the tasks
     */

    public ArrayList<MainTask> getAllTaskFromCategory(int CategoryId){
        ArrayList<MainTask> tasksByCategory = new ArrayList<>();
        for(MainTask task: tasks){
            if(task.getCategoryId() == CategoryId){
                tasksByCategory.add(task);
            }
        }
        return tasksByCategory;
    }

    /**
     * Gets all tasks
     * @return Returns all tasks
     */

    public ArrayList<MainTask> getAllTasks(){
        ArrayList<MainTask> allMainTasks = new ArrayList<>(tasks);
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
