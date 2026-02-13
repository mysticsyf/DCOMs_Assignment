/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcoms_assignment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginImpl extends UnicastRemoteObject implements Login{
    private StaffService service;
    
    protected LoginImpl(StaffService service) throws RemoteException{
        super();
        this.service = service;
    }

    @Override
    public Staff Login(String StaffID, String Password) throws RemoteException {
        return service.CheckLogin(StaffID, Password);
    }
}
