package ntnu.team1.application.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class that represents a main task
 * Inherits from the abstract super class Task
 *
 */

public class MainTask extends Task {
    private boolean hasCategory;
    private int categoryId;
    private boolean hasSecondaryTask;
    private ArrayList<SecondaryTask> secondaryTasks;
    private int secondaryTaskIdCount = 0;
    private long serialVersionUID;

    /**
     *
     * @param ID an Integer representing the ID of a main task
     * @param startDate A LocalDate object representing the start date of the Task
     * @param endDate A LocalDate object representing the end date of the Task
     * @param name A string representing the name of the Task
     * @param description A String representing the description of the Task
     * @param priority An integer representing the priority of the Task
     * @param categoryId An Integer representing the ID of a category
     */

    public MainTask(int ID, LocalDate startDate, LocalDate endDate, String name, String description, int priority, int categoryId) {
        super(ID, startDate, endDate, name, description, priority);
        this.secondaryTasks = new ArrayList<>();
        this.categoryId = categoryId;
        this.hasCategory = true;
        this.hasSecondaryTask = false;
    }

    public MainTask(int ID, LocalDate startDate, LocalDate endDate, String name, String description, int priority) {
        super(ID, startDate, endDate, name, description, priority);
        this.secondaryTasks = new ArrayList<>();
        this.categoryId = -1;
        this.hasCategory = true;
        this.hasSecondaryTask = false;
    }

    /**
     *
     * @return returns category ID
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     *
     * @return returns true if the main task has a category, false if not.
     */

    public boolean hasCategory() {
        return hasCategory;
    }

    /**
     *
     * @return returns true if main task has a secondary task, false if not.
     */

    public boolean hasSecondaryTask() {
        return hasSecondaryTask;
    }

    /**
     * Updates the category ID
     * @param categoryId
     */

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     *
     * @return returns all secondary tasks
     */

    public ArrayList<SecondaryTask> getSecondaryTasks() {
        return secondaryTasks;
    }

    /**
     * A method that adds a secondary task to the list 'secondaryTasks'
     * @param startDate
     * @param endDate
     * @param name
     * @param description
     * @param priority
     * @return returns true if secondary task s successfully added, false if not.
     */

    public boolean addSecondaryTask(LocalDate startDate, LocalDate endDate,String name, String description, int priority){
        SecondaryTask t = new SecondaryTask(secondaryTaskIdCount, startDate, endDate, name, description, priority);
        if (!secondaryTasks.contains(t)) {
            secondaryTasks.add(t);
            return true;
        }
        return false;
    }

    /**
     * A method that removes a secondary task if ID exist.
     * @param secondaryTaskId
     * @return returns true if removal was successful, false if not.
     */
    public boolean removeSecondaryTask(int secondaryTaskId){
        for(SecondaryTask t: secondaryTasks){
            if(t.getID() == secondaryTaskId){
                secondaryTasks.remove(t);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return returns information about MainTask objects.
     */
    @Override
    public String toString() {
        return "MainTask{" +
                "hasCategory=" + hasCategory +
                ", categoryId=" + categoryId +
                ", HasUnderTask=" + hasSecondaryTask +
                ", secondaryTasks=" + secondaryTasks +
                ", secondaryTaskIdCount=" + secondaryTaskIdCount +
                ", serialVersionUID=" + serialVersionUID +
                "} " + super.toString();
    }
}
