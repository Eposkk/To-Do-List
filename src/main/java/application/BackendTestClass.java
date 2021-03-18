package application;

import java.awt.*;

public class BackendTestClass {

    public static void main(String[] args){
        MainRegister register = new MainRegister();

        register.addCategory("Kategori_1", Color.pink);
        register.addCategory("Kategori_2", Color.blue);
        register.addCategory("Kategori_3", Color.red);
        register.addCategory("Kategori_4", Color.green);

        System.out.println(register.toString());


    }
}
