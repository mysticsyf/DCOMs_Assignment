package dcoms_assignment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;

import java.util.Map;
//test 2

public class ALImpl extends UnicastRemoteObject implements ApplyLeave {

    private LeaveService service;
    protected ALImpl(LeaveService leaveService) throws RemoteException {
        super();
        this.service = leaveService;
    }

    @Override
    public void applyLeave(Staff staff, LocalDate date1, LocalDate date2, String reason) throws RemoteException {
        service.ApplyLeave(staff, date1, date2, reason);
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
