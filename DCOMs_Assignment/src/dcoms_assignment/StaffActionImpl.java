/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcoms_assignment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StaffActionImpl extends UnicastRemoteObject implements StaffAction{
    private StaffService service;
    
    protected StaffActionImpl(StaffService service) throws RemoteException{
        super();
        this.service = service;
    }

    @Override
    public Staff Login(String StaffID, String Password) throws RemoteException {
        return service.CheckLogin(StaffID, Password);
    }
    
    @Override
    public String ViewProfile(Staff staff) throws RemoteException {
        return service.ViewProfile(staff);
    }
    
    @Override
    public String ViewProfile(String StaffID) throws RemoteException {
        return service.ViewProfile(StaffID);
    }
    
    @Override
    public String GetAllStaff() throws RemoteException {
        return service.getAllStaff();
    }
    
    @Override
    public String UpdateName(String ChosenID, String newName) throws RemoteException {
        return service.UpdateName(ChosenID, newName);
    }

    @Override
    public String UpdateRole(String ChosenID, String newRole) throws RemoteException {
        return service.UpdateRole(ChosenID, newRole);
    }

    @Override
    public String UpdatePassword(String ChosenID, String newPassword) throws RemoteException {
        return service.UpdatePassword(ChosenID, newPassword);
    }

    @Override
    public String UpdateSalary(String ChosenID, int newSalary) throws RemoteException {
        return service.UpdateSalary(ChosenID, newSalary);
    }

    @Override
    public String UpdateLeaves(String ChosenID, int newRemLeave) throws RemoteException {
        return service.UpdateLeaves(ChosenID, newRemLeave);
    }
    

}
