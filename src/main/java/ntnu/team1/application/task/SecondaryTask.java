package ntnu.team1.application.task;

import java.time.LocalDate;

/**
 * A class that represents a secondary task, under a main task
 * Inherits from the abstract super class Task
 */

public class SecondaryTask extends Task {


    /**
     *
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


}
