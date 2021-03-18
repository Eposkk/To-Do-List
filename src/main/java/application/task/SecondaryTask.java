package application.task;

import java.time.LocalDate;

public class SecondaryTask extends Task {

    public SecondaryTask(int ID, LocalDate date, String name, String description, int priority) {
        super(ID, date, name, description, priority);
    }
}
