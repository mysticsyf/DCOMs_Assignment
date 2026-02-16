package dcoms_assignment;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args){
        try{
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            
            Login login = (Login)reg.lookup("Login");
            
            Staff CurrentUser = login.Login("HR01", "1234");

            if(CurrentUser != null){
                System.out.println("Currently logged in as "+CurrentUser.getName());
            }else{
                System.out.println("Login failed");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
