package application.event;

import java.time.LocalDate;

public class SecondaryEvent extends Event {

    public SecondaryEvent(int ID, LocalDate date, String name, String description, int priority) {
        super(ID, date, name, description, priority);
    }
}
