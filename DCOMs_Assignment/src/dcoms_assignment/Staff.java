package dcoms_assignment;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Staff implements Serializable{
    private static final long serialVersionUID = 1L;
    String StaffID;
    String Name;
    String Role;
    String Password;

    public Staff(String StaffID, String Name, String Role) {
        this.StaffID = StaffID;
        this.Name = Name;
        this.Role = Role;
        this.Password = "abc";
    }

    public String getName() {
        return Name;
    }

    public String getRole() {
        return Role;
    }

    public String getStaffID() {
        return StaffID;
    }

    public String getPassword() {
        return Password;
    }
    
    public static void main(String[] args){
        Staff Staff1 = new Staff("S1","Bob", "IT");

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
