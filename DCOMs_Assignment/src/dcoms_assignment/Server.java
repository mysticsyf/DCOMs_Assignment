package dcoms_assignment;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args){
        try{
            Registry reg = LocateRegistry.createRegistry(1099); //creating a server under 1099
            
            Identify Action1 = new IdentifyImpl();  //create object based on action class
            
            reg.rebind("SelfIdentify", Action1); 
            //basically adding this as an action client can find and use
            
            System.out.println("Server is now running... ");
            //ok now we try having 2 diff ppl access server
            //run this class first
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
