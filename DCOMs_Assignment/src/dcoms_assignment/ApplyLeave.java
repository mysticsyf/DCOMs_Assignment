package dcoms_assignment;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;


public interface ApplyLeave extends Remote{
    String applyLeave(Leaves leave) throws RemoteException;
    String ApproveLeave(String leaveId) throws RemoteException;
    String RejectLeave(String leaveId) throws RemoteException;
    Map<String, Leaves> getAllLeaves() throws RemoteException;
}