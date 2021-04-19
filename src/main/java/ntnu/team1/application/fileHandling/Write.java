package ntnu.team1.application.fileHandling;


import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.MainTask;
import ntnu.team1.application.task.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Write {
    private final MainRegister register;

    public Write(MainRegister register){
        this.register=register;
    }

    public void writeRegister(){
        FileOutputStream fileOutRegister = null;
        ObjectOutputStream out = null;
        try {
            fileOutRegister = new FileOutputStream("data/mainRegister.ser");
            out = new ObjectOutputStream(fileOutRegister);
            out.writeObject(register);
        } catch (IOException i){
            System.out.println(i.getMessage());
        }
        finally {
            try {
                out.close();
                fileOutRegister.close();
            }catch (IOException i){
                System.out.println(i.getMessage());
            }
        }

    }

}

