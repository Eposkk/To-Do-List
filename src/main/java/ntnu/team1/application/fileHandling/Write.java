package ntnu.team1.application.fileHandling;

import ntnu.team1.application.MainRegister;
import java.io.*;

/**
 * Class for writing the register to a file
 */

public class Write {

    /**
     * Main register to write
     */
    private final MainRegister register;

    /**
     * Constructor for class, sets the register
     * @param register Register that is written
     */
    public Write(MainRegister register){
        this.register=register;
    }

    /**
     * Method for writing the register to a file
     */
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

