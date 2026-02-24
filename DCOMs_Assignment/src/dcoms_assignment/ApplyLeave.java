package dcoms_assignment;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ApplyLeave extends Remote{
    String applyLeave(Leaves leave) throws RemoteException;
}