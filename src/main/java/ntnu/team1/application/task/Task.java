package ntnu.team1.application.task;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Abstarct class task, keeps track of a specific task. Implements serializable to be able to store the data.
 *
 */

public abstract class Task implements Serializable {
    private static final long serialVersionUID = 5595467663718973084L;
    private final int ID;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private String description;
    private int priority;
    private boolean isDone;


    /**
     * Creates a task with start date and end date
     * @param ID An integer representing the ID of the Task
     * @param startDate A LocalDate object representing the start date of the Task
     * @param endDate A LocalDate object representing the end date of the Task
     * @param name A string representing the name of the Task
     * @param description A String representing the description of the Task
     * @param priority An integer representing the priority of the Task
     */
    public Task( int ID,LocalDate startDate, LocalDate endDate, String name, String description, int priority) {
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.isDone = false;
    }
   /*
    public Task(int ID, String name, String description, int priority) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        //TODO add priority check
        this.priority = priority;
        done = false;
    }


    public Task(int ID, LocalDate endDate, String name, String description, int priority) {
        this.ID = ID;
        this.endDate = endDate;
        this.name = name;
        this.description = description;
        this.priority = priority;
        done = false;
    }*/

    /**
     * Gets the id
     * @return id
     */


    public int getID(){
        return ID;
    }

    /**
     * Gets the start date
     * @return startDate
     */

    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date
     * @return endDate
     */

    public LocalDate getEndDate(){
        return endDate;
    }

    /**
     * Gets the name
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * Gets the description
     * @return description
     */

    public String getDescription() {
        return description;
    }

    /**
     * Gets the priority
     * @return priority
     */

    public int getPriority() {
        return priority;
    }

    /**
     * Sets the start date
     * @param startDate A LocalDate object
     */

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the end date
     * @param endDate A LocalDate object
     */

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the name
     * @param name A String
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description
     * @param description A String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the priority
     * @param priority An integer
     */

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Sets a tasks status to done
     * @param done A boolean
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }


    /**
     * Equals method for the class
     * @return a boolean according to if an object matches this object
     */

    public Boolean isDone(){
        return isDone;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return ID == task.ID &&
                priority == task.priority &&
                isDone == task.isDone &&
                Objects.equals(startDate, task.startDate) &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, startDate, name, description, priority, isDone);
    }


    /**
     *To string for the class
     * @return A string containing all relevant information from the class
     */

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", start date=" + startDate +
                ", end date=" + endDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", done=" + isDone +
                '}';
    }
}
