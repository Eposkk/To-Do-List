package ntnu.team1.backend;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import ntnu.team1.backend.exceptions.RemoveException;
import ntnu.team1.backend.objects.Category;
import ntnu.team1.backend.objects.Task;

/**
 * This is is the main register of the application
 * It keeps track of all categories in a HashMap
 * and tasks in an ArrayList
 * The Mainregister also allows for adding tasks and adding categories to the register
 * It also handles saving and reading from file, it also sorts the registers
 */

public class MainRegister implements Serializable {
    /**
     * HashMap used for storing categories, ID used as key
     */
    private HashMap<Integer, Category> categories;
    /**
     * ArrayList used for storing tasks
     */
    private ArrayList<Task> tasks;
    /**
     * Count of tasks in ArrayList
     */
    private int taskIdCount = 0;
    /**
     * Count of categories in HashMap
     */
    private int categoryIdCount = 0;

    /**
     * Constructor for MainRegister
     * Instantiates categories and tasks
     */

    public MainRegister(){
        categories = new HashMap<>();
        categories.put(-1, new Category(-1, Color.GREY,"No category"));
        tasks = new ArrayList<>();
    }

    /**
     * Returns a category according to the id
     * @param id id of the category
     * @return the category associated with the id
     */
    public Category getCategory(int id){
        return categories.get(id);
    }

    /**
     * Returns a task according to the id
     * @param id id of the task
     * @return the task associated with the id
     */

    public Task getMainTask(int id) throws IllegalArgumentException{
        for (Task task : tasks) {
            if (task.getID() == id) {
                return task;
            }
        }
        throw new IllegalArgumentException("No task found with the suggested Id.");
    }

    /**
     * Method for adding a task to the register
     * @param startDate A LocalDate object representing the start date of the Task
     * @param endDate A LocalDate object representing the end date of the Task
     * @param name A string representing the name of the Task
     * @param description A String representing the description of the Task
     * @param priority An integer representing the priority of the Task
     * @param categoryId An Integer representing the ID of a category
     * @throws NullPointerException If the name is null the Task will not be added
     */


    public void addMainTask(LocalDate startDate, LocalDate endDate, String name, String description,
                               int priority, int categoryId) throws NullPointerException{
        if(name.equals("")){
            throw new NullPointerException("Name cannot be null");
        }

            Task task = new Task(taskIdCount, name, description,  startDate,  endDate,  priority,  categoryId);
            tasks.add(task);
            taskIdCount+=1;
    }

    /**
     * Method for editing a task
     * @param id an Integer representing the ID of a main task
     * @param startDate A LocalDate object representing the start date of the Task
     * @param endDate A LocalDate object representing the end date of the Task
     * @param name A string representing the name of the Task
     * @param description A String representing the description of the Task
     * @param priority An integer representing the priority of the Task
     * @param categoryId An Integer representing the ID of a category
     */
    public void editMainTask(int id, LocalDate startDate, LocalDate endDate, String name, String description,
                             int priority, int categoryId){
        if(name.equals("")){
            throw new NullPointerException("Name cannot be null");
        }else{
            getMainTask(id).setName(name);
            getMainTask(id).setDescription(description);
            getMainTask(id).setPriority(priority);
            getMainTask(id).setStartDate(startDate);
            getMainTask(id).setEndDate(endDate);
            getMainTask(id).setCategoryId(categoryId);

           //tasks.remove(getMainTask(id));
           //tasks.add(new MainTask(id, name, description, startDate,endDate,priority,categoryId));
        }
    }

    /**
     * Method for editing a category
     * @param updatedCategory The category we want to replace with existing one
     */

    public void editCategory(Category updatedCategory){
        categories.replace(updatedCategory.getID(), updatedCategory);

    }

    /**
     * Removes a task from the register
     * @param mainTaskId Id associated with the task
     */

    public void removeMainTask(int mainTaskId) throws IllegalArgumentException{
        tasks.remove(getMainTask(mainTaskId));
    }

    /**
     * Gets all task from a given category
     * @param categoryId Id Associated with category
     * @return Returns an Arraylist with all the tasks
     */

    public ArrayList<Task> getAllTasksFromCategory(int categoryId) throws IllegalArgumentException{
        if(!categories.containsKey(categoryId)){
            throw new IllegalArgumentException("No task found with the suggested Id.");
        }
        return (ArrayList<Task>)tasks.stream().filter(task -> task.getCategoryId() == categoryId).collect(Collectors.toList());
    }

    /**
     * Gets all tasks
     * @return Returns all tasks
     */

    public ArrayList<Task> getAllTasks(){
        return new ArrayList<>(tasks);
    }

    /**
     *Changes the priority of a task
     * @param mainTaskId Id of the task
     * @param newPriority The priority of the task
     * @throws IllegalArgumentException Priority can only be in the range of 1,3
     */

    public void changePriorityMainTask(int mainTaskId, int newPriority) throws IllegalArgumentException {
        if(newPriority<1 || newPriority > 3){
            throw new IllegalArgumentException("Priority must be a number in the range [1,3]");
        }
        getMainTask(mainTaskId).setPriority(newPriority);
    }

    /**
     * Changes description
     * @param mainTaskId Task
     * @param newDescription New description
     * @throws IllegalArgumentException gets thrown if it is illegal
     */

    public void changeDescriptionMainTask(int mainTaskId, String newDescription) throws IllegalArgumentException{
        getMainTask(mainTaskId).setDescription(newDescription);
    }
    /**
     * Adds a category to the register
     * @param name The name of the category
     * @param color The color associated with the category
     * @return Returns true if the category was registered, returns false if it failed
     */

    public boolean addCategory(String name, Color color) throws NullPointerException{
        if(name.equals("")){
            throw new NullPointerException("Name cannot be null");
        }else{
            categories.put(categoryIdCount, new Category(categoryIdCount, color, name));
            categoryIdCount+=1;
            return true;
        }
    }

    /**
     * Gets the categories
     * @return Catgories
     */

    public HashMap<Integer, Category> getCategories() {
        return categories;
    }

    /**
     * Sets the category color
     * @param id Id of the category
     * @param color Color you want to set
     */

    public void setCategoryColor(int id, Color color) throws IllegalArgumentException{
        if(!categories.containsKey(id)){
            throw new IllegalArgumentException("Category does not exist");
        }
        categories.get(id).setColor(color);
    }


    /**
     * Sets the taks category
     * @param taskId Id associated with the task you want to set a category for
     * @param newCategoryId The category id you want to set to the task
     */

    public void setMainTaskCategory(int taskId, int newCategoryId) throws IllegalArgumentException{
        if(!categories.containsKey(newCategoryId)){
            throw new IllegalArgumentException("Category does not exist");
        }
        getMainTask(taskId).setCategoryId(newCategoryId);
    }

    /**
     * Removes a category
     * @param id Id of category
     * @throws RemoveException Thrown if it cant remove
     */

    public void removeCategory(int id) throws RemoveException{
        if(!categories.containsKey(id)){
            throw new RemoveException("Category does not exist");
        }
        categories.remove(id);
    }

    /**
     * Sets the category list
     * @param categories Category List we want to set
     */

    public void setCategories(HashMap<Integer, Category> categories) {
        this.categories = categories;
    }

    /**
     * Sets the task list
     * @param tasks Task list we want to set
     */

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * To-String for the object
     * @return All usefull information as a String
     */

    @Override
    public String toString() {
        return "MainRegister" +
                "\ncategories=" + categories +
                "\ntasks=" + tasks +
                "\ntaskIdCount=" + taskIdCount +
                "\ncategoryIdCount=" + categoryIdCount;
    }
}
