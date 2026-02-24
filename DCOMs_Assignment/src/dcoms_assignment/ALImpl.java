package dcoms_assignment;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ALImpl extends UnicastRemoteObject implements ApplyLeave{
    private LeaveService service;
    
    protected ALImpl(LeaveService leaveService) throws RemoteException{
        super();
        this.service = leaveService;
    }
    
    @Override
    public String applyLeave(Leaves leave) throws RemoteException{
        service.ApplyLeave(leave);
        return "Leave applied successfully for Staff ID: " + leave.getStaffId();
    }
}
