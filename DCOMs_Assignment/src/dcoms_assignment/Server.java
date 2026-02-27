package dcoms_assignment;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;


public class Server {
    public static void main(String[] args){
        try{
            //Leave Stuff *change place/remove later
            LeaveService leaveService = new LeaveService();
            ALImpl applyLeaveImpl = new ALImpl(leaveService);
            Registry reg = LocateRegistry.createRegistry(1099); //creating a server under 1099
            StaffService SS = new StaffService();
            
            //Example for Leaves
            if (leaveService.getAllLeaves().isEmpty()) {
                Leaves leave1 = new Leaves(
                        "L001",
                        "ST01",
                        LocalDate.of(2026, 3, 1),
                        LocalDate.of(2026, 3, 3),
                        "Medical Leave",
                        "APPROVED"
                );

                Leaves leave2 = new Leaves(
                        "L002",
                        "HR01",
                        LocalDate.of(2026, 3, 5),
                        LocalDate.of(2026, 3, 6),
                        "Personal Leave",
                        "PENDING"
                );

                leaveService.ApplyLeave(leave1);
                leaveService.ApplyLeave(leave2);

                System.out.println("Default leaves created.");
            }
            
            if (SS.getAllStaff().isEmpty()) {

                Staff hr = new Staff("HR01", "Alice", "HR", "1234");
                Staff staff = new Staff("ST01", "Bob", "STAFF", "1234");

                SS.AddStaff(hr);
                SS.AddStaff(staff);

                System.out.println("Default staff created.");
            }
            
            Login LoginAction = new LoginImpl(SS);
            
//            Identify Action1 = new IdentifyImpl();  //create object based on action class
            
            reg.rebind("Login", LoginAction); 
            reg.rebind("ApplyLeave", applyLeaveImpl);

            //basically adding this as an action client can find and use
            
            System.out.println("Server is now running... ");
            //ok now we try having 2 diff ppl access server
            //run this class first
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

