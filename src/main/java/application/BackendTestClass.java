package application;

import application.task.Category;
import application.task.MainTask;
import com.sun.tools.javac.Main;

import java.awt.*;
import java.time.LocalDate;
import java.util.Random;

public class BackendTestClass {

    public static void main(String[] args){
        MainRegister register = new MainRegister();

        register.addCategory("Kategori_1", Color.pink);
        register.addCategory("Kategori_2", Color.blue);
        register.addCategory("Kategori_3", Color.red);
        register.addCategory("Kategori_4", Color.green);


        for(int i = 0; i<=100;i++){
            LocalDate date = null;
            String name = "task " + i;
            String description = "Lorem Ipsum";
            Random random  = new Random();

            register.addMainTask(date, name, description, random.nextInt(3), random.nextInt(3));
        }

        for(int i: register.getCategories().keySet()){
            System.out.println(register.getCategories().get(i));
        }
        register.sortByPriority();
        for(MainTask m: register.getAllTask()){

            System.out.println(m);
        }



    }
}
