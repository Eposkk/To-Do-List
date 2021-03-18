package application.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class MainTask extends Task {
    private boolean hasCategory;
    private int categoryId;
    private boolean HasUnderTask;
    private ArrayList<SecondaryTask> secondaryTasks;
    private int secondaryTaskIdCount=0;
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

    public ArrayList<SecondaryTask> getSecondaryTasks() {
        return secondaryTasks;
    }

    public boolean addSecondaryTask(LocalDate date,String name, String description, int priority){
        SecondaryTask t = new SecondaryTask(secondaryTaskIdCount, date, name, description, priority);
        if (!secondaryTasks.contains(t)) {
            secondaryTasks.add(t);
        }
        return false;
    }
    public boolean removeSecondaryTask(int secondaryTaskId){
        for(SecondaryTask t: secondaryTasks){
            if(t.getID() == secondaryTaskId){
                secondaryTasks.remove(t);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "MainTask{" +
                "hasCategory=" + hasCategory +
                ", categoryId=" + categoryId +
                ", HasUnderTask=" + HasUnderTask +
                ", secondaryTasks=" + secondaryTasks +
                ", secondaryTaskIdCount=" + secondaryTaskIdCount +
                ", serialVersionUID=" + serialVersionUID +
                "} " + super.toString();
    }
}
