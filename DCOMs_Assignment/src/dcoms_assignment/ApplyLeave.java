package dcoms_assignment;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Map;


public interface ApplyLeave extends Remote{
    void applyLeave(Staff staff, LocalDate date1, LocalDate date2, String reason) throws RemoteException;
    String ApproveLeave(String leaveId) throws RemoteException;
    String RejectLeave(String leaveId) throws RemoteException;
    Map<String, Leaves> getAllLeaves() throws RemoteException;
}