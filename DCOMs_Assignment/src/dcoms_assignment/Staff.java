package dcoms_assignment;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Staff implements Serializable{
    private static final long serialVersionUID = 1L;
    String Name;
    String Role;

    public Staff(String Name, String Role) {
        this.Name = Name;
        this.Role = Role;
    }

    public String getName() {
        return Name;
    }

    public String getRole() {
        return Role;
    }
    
    public static void main(String[] args){
        Staff Staff1 = new Staff("Bob", "IT");

        try{
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            Identify Action = (Identify)reg.lookup("SelfIdentify");

            String greet = Action.Identify(Staff1);
            System.out.println(greet);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
   
}
