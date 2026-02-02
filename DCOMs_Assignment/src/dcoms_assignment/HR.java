package dcoms_assignment;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HR extends Staff implements Serializable{   //lets have an HR say hi
    
    public HR(String Name){
        super(Name, "HR");
    }

    public static void main(String[] args) {
        HR hr1 = new HR("Hannah");
        try{
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            Identify Action = (Identify)reg.lookup("SelfIdentify");

            String greet = Action.Identify(hr1);
            System.out.println(greet);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
