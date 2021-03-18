package application.task;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * asbtract class Task
 * General class for all tasks
 */
public abstract class Task implements Serializable {
    private static final long serialVersionUID = 5595467663718973084L;
    private final int ID;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private String description;
    private int priority;
    private boolean done;


    /**
     * Creates a task with start date and end date
     * @param ID
     * @param startDate
     * @param endDate
     * @param name
     * @param description
     * @param priority
     */
    public Task(int ID, LocalDate startDate, LocalDate endDate, String name, String description, int priority) {
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.done = false;
    }
    /**
     * Creates a secondary task without dates
     * @param ID
     * @param name
     * @param description
     * @param priority
     */
    public Task(int ID, String name, String description, int priority) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        //TODO add priority check
        this.priority = priority;
        done = false;
    }

    /**
     * Creates a secondary task with an end date
     * @param ID
     * @param endDate
     * @param name
     * @param description
     * @param priority
     */
    public Task(int ID, LocalDate endDate, String name, String description, int priority) {
        this.ID = ID;
        this.endDate = endDate;
        this.name = name;
        this.description = description;
        this.priority = priority;
        done = false;
    }


    public int getID(){
        return ID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Sets a tasks status to done
     * @param done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return ID == task.ID &&
                priority == task.priority &&
                done == task.done &&
                Objects.equals(startDate, task.startDate) &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, startDate, name, description, priority, done);
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", start date=" + startDate +
                ", end date=" + endDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", done=" + done +
                '}';
    }
}
