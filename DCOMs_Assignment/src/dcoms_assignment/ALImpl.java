package dcoms_assignment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.Map;
//test 2

public class ALImpl extends UnicastRemoteObject implements ApplyLeave {

    private LeaveService service;
    protected ALImpl(LeaveService leaveService) throws RemoteException {
        super();
        this.service = leaveService;
    }

    @Override
    public String applyLeave(Leaves leave) throws RemoteException {
        service.ApplyLeave(leave);
        return "\nLeave applied successfully for Staff ID: " + leave.getStaffId() + "\n Your Leave ID is: " + leave.getLeaveId();
    }

    @Override
    public Map<String, Leaves> getAllLeaves() throws RemoteException {
        return service.getAllLeaves();
    }

    @Override
    public String ApproveLeave(String leaveId) throws RemoteException {
        return service.ApproveLeave(leaveId);
    }

    @Override
    public String RejectLeave(String leaveId) throws RemoteException {
        return service.RejectLeave(leaveId);
    }

}
