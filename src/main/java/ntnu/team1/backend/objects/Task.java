package ntnu.team1.backend.objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A class that represents a main task
 * Inherits from the abstract super class Task
 *
 */

public class Task implements Serializable {
    /**
     * Id for the task
     */
    private final int ID;
    /**
     * Indicates if the task has category
     */
    private boolean hasCategory=false;
    /**
     * Name of the task
     */
    private String name;
    /**
     * Description of the task
     */
    private String description;
    /**
     * Start date of the task
     */
    private LocalDate startDate;
    /**
     * End date of the task
     */
    private LocalDate endDate;
    /**
     * Priority of the task
     */
    private int priority;
    /**
     * Category id ascoiacted with the task
     */
    private int categoryId;
    /**
     * Indicates if the task is done
     */
    private boolean isDone;
    /**
     * Id for storing the task
     */
    private final long serialVersionUID;

    /**
     *Constructor of the objects
     *
     * @param ID an Integer representing the ID of a main task
     * @param startDate A LocalDate object representing the start date of the Task
     * @param endDate A LocalDate object representing the end date of the Task
     * @param name A string representing the name of the Task
     * @param description A String representing the description of the Task
     * @param priority An integer representing the priority of the Task
     * @param categoryId An Integer representing the ID of a category
     */

    public Task(int ID, String name, String description, LocalDate startDate, LocalDate endDate, int priority, int categoryId) {
        this.ID = ID;
        if(categoryId> -1){
            this.hasCategory = true;
        }
        this.categoryId = categoryId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.isDone = false;
        serialVersionUID =  -68497944707546120L;
    }



    /**
     * Gets the category ID
     * @return returns category ID
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Checks if the task has a category
     * @return returns true if the main task has a category, false if not.
     */

    public boolean hasCategory() {
        return hasCategory;
    }



    /**
     * Updates the category ID
     * @param categoryId Id of the category
     */

    public void setCategoryId(int categoryId) {
        if(categoryId > -1){
            hasCategory = true;
        }else{
            hasCategory = false;
        }
        this.categoryId = categoryId;
    }

    /**
     * Gets the ID
     * @return an int
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets start date
     * @return A Local Date Object
     */

    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets start date
     * @param startDate Start date of the task
     */

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date
     * @return A Local Date Object
     */

    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets end date
     * @param endDate A Local Date Object
     */

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets name
     * @return A String
     */

    public String getName() {
        return name;
    }

    /**
     * Sets name
     * @param name Name of the task
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description
     * @return Description of the task
     */

    public String getDescription() {
        return description;
    }

    /**
     * Sets description
     * @param description Description as a String
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets priority
     * @return Priority as an int
     */

    public int getPriority() {
        return priority;
    }

    /**
     * Sets priority
     * @param priority Priority as an int
     */

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Checks if task is done
     * @return A boolean indicating if the task is done
     */

    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets task as done
     * @param done a boolean indicating if the task is done
     */

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Equals method for the object
     * @param o Object we want to compare with
     * @return A boolean indicating if the objects are equal
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return ID == task.ID && hasCategory == task.hasCategory && priority == task.priority && categoryId == task.categoryId && isDone == task.isDone && Objects.equals(name, task.name) && Objects.equals(description, task.description) && Objects.equals(startDate, task.startDate) && Objects.equals(endDate, task.endDate);
    }


    /**
     * ToString for the object
     * @return returns information about MainTask objects.
     */
    @Override
    public String toString() {
        return "MainTask{" +
                "ID=" + ID +
                ", hasCategory=" + hasCategory +
                ", categoryId=" + categoryId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", isDone=" + isDone +
                '}';
    }
}
