package application.event;

import java.time.LocalDate;
import java.util.HashMap;

public class MainEvent extends Event {
    private boolean hasCategory;
    private int categoryId;
    private boolean HasUnderEvent;
    private HashMap<Integer, SecondaryEvent> secondaryEvents;
    private long serialVersionUID;

    public MainEvent(int ID, LocalDate date, String name, String description, int priority, int categoryId) {
        super(ID, date, name, description, priority);
        this.categoryId = categoryId;
        this.hasCategory = true;
    }

    public MainEvent(int ID, LocalDate date, String name, String description, int priority) {
        super(ID, date, name, description, priority);
        this.hasCategory = false;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public boolean hasCategory() {
        return hasCategory;
    }

    public boolean hasUnderEvent() {
        return HasUnderEvent;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public HashMap<Integer, SecondaryEvent> getSecondaryEvents() {
        return secondaryEvents;
    }


    //TODO make method
    public boolean addSecondaryEvent(){
        return true;
    }

    @Override
    public String toString() {
        return "MainEvent{" +
                "hasCategory=" + hasCategory +
                ", categoryId=" + categoryId +
                ", HasUnderEvent=" + HasUnderEvent +
                "} " + super.toString();
    }

}
