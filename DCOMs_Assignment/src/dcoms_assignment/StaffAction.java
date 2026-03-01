package dcoms_assignment;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StaffAction extends Remote{
    Staff Login(String StaffID, String password) throws RemoteException;
    String ViewProfile(Staff staff) throws RemoteException;
    String GetAllStaff() throws RemoteException;
//    String UpdateStaff(String ChosenID) throws RemoteException;
    String UpdateName(String ChosenID, String newName) throws RemoteException;
    String UpdateRole(String ChosenID, String newRole) throws RemoteException;
    String UpdatePassword(String ChosenID, String newPassword) throws RemoteException;
    String UpdateSalary(String ChosenID, int newSalary) throws RemoteException;
    String UpdateLeaves(String ChosenID, int newRemLeave) throws RemoteException;
}
