package application.event;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Event {
    private final int ID;
    private LocalDate date;
    private String name;
    private String description;
    private int priority;
    private boolean done;

    public Event(int ID, LocalDate date, String name, String description, int priority) {
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

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return ID == event.ID &&
                priority == event.priority &&
                done == event.done &&
                Objects.equals(date, event.date) &&
                Objects.equals(name, event.name) &&
                Objects.equals(description, event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, date, name, description, priority, done);
    }

    @Override
    public String toString() {
        return "Event{" +
                "ID=" + ID +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", done=" + done +
                '}';
    }
}
