package ntnu.team1.application.fileHandling;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import ntnu.team1.application.MainRegister;
import ntnu.team1.application.task.Category;
import ntnu.team1.application.task.MainTask;

public class Read {
    String pathRegister;
    public Read(String pathRegister){
        this.pathRegister=pathRegister;
    }

    public MainRegister readRegister(){
        MainRegister register = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream in = null;
        try{
            fileInputStream = new FileInputStream(pathRegister);
            in= new ObjectInputStream(fileInputStream);
            register=(MainRegister)in.readObject();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException c){
            System.out.println(c.getMessage());
        }
        finally { //The files will close even tough an exception will be caught when reading from task files.
            try{
                fileInputStream.close();
                in.close();
            }
            catch (IOException i){
                System.out.println(i.getMessage());
            }
        }
        return register;
    }
}

