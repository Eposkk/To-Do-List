package application.task;

import java.time.LocalDate;

/**
 * SecondaryTask class
 * A task within another task
 */
public class SecondaryTask extends Task {

    public SecondaryTask(int ID, LocalDate startDate, LocalDate endDate, String name, String description, int priority) {
        super(ID, startDate, endDate, name, description, priority);
    }

    public SecondaryTask(int ID, String name, String description, int priority) {
        super(ID, name, description, priority);
    }

    public SecondaryTask(int ID, LocalDate endDate, String name, String description, int priority) {
        super(ID, endDate, name, description, priority);
    }
}
