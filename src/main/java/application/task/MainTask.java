package application.task;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainTask extends Task {
    private boolean hasCategory;
    private int categoryId;
    private boolean hasSecondaryTask;
    private ArrayList<SecondaryTask> secondaryTasks;
    private int secondaryTaskIdCount = 0;
    private long serialVersionUID;

    public MainTask(int ID, LocalDate startDate, LocalDate endDate, String name, String description, int priority, int categoryId) {
        super(ID, startDate, endDate, name, description, priority);
        this.secondaryTasks = new ArrayList<>();
        this.categoryId = categoryId;
        this.hasCategory = true;
        this.hasSecondaryTask = false;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public boolean hasCategory() {
        return hasCategory;
    }

    public boolean hasUnderTask() {
        return hasSecondaryTask;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public ArrayList<SecondaryTask> getSecondaryTasks() {
        return secondaryTasks;
    }

    public boolean addSecondaryTask(LocalDate startDate, LocalDate endDate,String name, String description, int priority){
        SecondaryTask t = new SecondaryTask(secondaryTaskIdCount, startDate, endDate, name, description, priority);
        if (!secondaryTasks.contains(t)) {
            secondaryTasks.add(t);
            return true;
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
                ", HasUnderTask=" + hasSecondaryTask +
                ", secondaryTasks=" + secondaryTasks +
                ", secondaryTaskIdCount=" + secondaryTaskIdCount +
                ", serialVersionUID=" + serialVersionUID +
                "} " + super.toString();
    }
}
