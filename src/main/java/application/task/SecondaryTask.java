package application.task;

import java.time.LocalDate;

/**
 * SecondaryTask class
 * A task within another task
 */
public class SecondaryTask extends Task {
    /**
     * Creates a secondary task with start date and end date
     * @param ID
     * @param startDate
     * @param endDate
     * @param name
     * @param description
     * @param priority
     */
    public SecondaryTask(int ID, LocalDate startDate, LocalDate endDate, String name, String description, int priority) {
        super(ID, startDate, endDate, name, description, priority);
    }

    /**
     * Creates a secondary task without dates
     * @param ID
     * @param name
     * @param description
     * @param priority
     */
    public SecondaryTask(int ID, String name, String description, int priority) {
        super(ID, name, description, priority);
    }

    /**
     * Creates a secondary task with only an end date
     * @param ID
     * @param endDate
     * @param name
     * @param description
     * @param priority
     */
    public SecondaryTask(int ID, LocalDate endDate, String name, String description, int priority) {
        super(ID, endDate, name, description, priority);
    }
}
