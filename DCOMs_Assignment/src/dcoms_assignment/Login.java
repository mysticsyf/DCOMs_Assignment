package dcoms_assignment;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Login extends Remote{
    Staff Login(String StaffID, String password) throws RemoteException;
}
