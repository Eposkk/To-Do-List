package application.task;

import java.time.LocalDate;
import java.util.HashMap;

public class MainTask extends Task {
    private boolean hasCategory;
    private int categoryId;
    private boolean HasUnderTask;
    private HashMap<Integer, SecondaryTask> secondaryTasks;
    private long serialVersionUID;

    public MainTask(int ID, LocalDate date, String name, String description, int priority, int categoryId) {
        super(ID, date, name, description, priority);
        this.categoryId = categoryId;
        this.hasCategory = true;
    }

    public MainTask(int ID, LocalDate date, String name, String description, int priority) {
        super(ID, date, name, description, priority);
        this.hasCategory = false;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public boolean hasCategory() {
        return hasCategory;
    }

    public boolean hasUnderTask() {
        return HasUnderTask;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public HashMap<Integer, SecondaryTask> getSecondaryTasks() {
        return secondaryTasks;
    }


    //TODO make method
    public boolean addSecondaryTask(){
        return true;
    }

    @Override
    public String toString() {
        return "MainTask{" +
                "hasCategory=" + hasCategory +
                ", categoryId=" + categoryId +
                ", HasUnderTask=" + HasUnderTask +
                "} " + super.toString();
    }

}
