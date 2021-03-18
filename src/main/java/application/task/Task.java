package application.task;

import java.time.LocalDate;
import java.util.Objects;

/**
 * asbtract class Task
 * General class for all tasks
 */
public abstract class Task {
    private final int ID;
    private LocalDate date;
    private String name;
    private String description;
    private int priority;
    private boolean done;

    public Task(int ID, LocalDate date, String name, String description, int priority) {
        this.ID = ID;
        this.date = date;
        this.name = name;
        this.description = description;
        this.priority = priority;
        done = false;
    }

    public int getID(){
        return ID;
    }

    public LocalDate getDate() {
        return date;
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

    public void setDate(LocalDate date) {
        this.date = date;
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
                Objects.equals(date, task.date) &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, date, name, description, priority, done);
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", done=" + done +
                '}';
    }
}
