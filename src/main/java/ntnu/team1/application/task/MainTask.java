package ntnu.team1.application.task;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A class that represents a main task
 * Inherits from the abstract super class Task
 *
 */

public class MainTask implements Serializable {
    private final int ID;
    private boolean hasCategory=false;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int priority;
    private int categoryId;
    private boolean isDone;
    private final long serialVersionUID;

    public MainTask(int ID, String name, String description, LocalDate startDate, LocalDate endDate, int priority, int categoryId) {
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
     *
     * @param ID an Integer representing the ID of a main task
     * @param startDate A LocalDate object representing the start date of the Task
     * @param endDate A LocalDate object representing the end date of the Task
     * @param name A string representing the name of the Task
     * @param description A String representing the description of the Task
     * @param priority An integer representing the priority of the Task
     * @param categoryId An Integer representing the ID of a category
     */

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
     * Updates the category ID
     * @param categoryId
     */

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getID() {
        return ID;
    }

    public boolean isHasCategory() {
        return hasCategory;
    }

    public void setHasCategory(boolean hasCategory) {
        this.hasCategory = hasCategory;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     *
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
