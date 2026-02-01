package dcoms_assignment;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StaffIdentify {
       public static void main(String[] args){
        try{
            Registry reg = LocateRegistry.getRegistry(null);
            Identify Action = (Identify) reg.lookup("rmi://localhost/SelfIdentify");
            String SelfIdentify = "This is normal staff calling in!";
            String Hi = Action.Identify();
            System.out.println(Hi + SelfIdentify);
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
   
}
