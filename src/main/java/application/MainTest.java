package application;

import application.event.MainEvent;

import java.awt.*;
import java.time.LocalDate;

public class MainTest {
    public static void main(String[] args){
        MainRegister register = new MainRegister();

        register.addMainEvent(LocalDate.of(2020,12,12), "test", "Lorem Ipsum", 5, 0);
        register.addMainEvent(LocalDate.of(2020,12,12), "test", "Lorem Ipsum", 3, 1);
        register.addMainEvent(LocalDate.of(2020,12,12), "test", "Lorem Ipsum", 2, 1);
        register.addMainEvent(LocalDate.of(2020,12,12), "test", "Lorem Ipsum", 1, 0);
        register.addMainEvent(LocalDate.of(2020,12,12), "test", "Lorem Ipsum", 5, 1);

        register.addCategory("Kategori1", Color.PINK );
        register.addCategory("Kategori2", Color.BLUE );

        register.sortByPriority();
        System.out.println(register.toString());

        register.sortByCategory();
        System.out.println(register.toString());

    }
}
