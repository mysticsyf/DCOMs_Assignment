package dcoms_assignment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IdentifyImpl extends UnicastRemoteObject implements Identify{
    protected IdentifyImpl() throws RemoteException{
        super();
    }
    
    @Override
    public String Identify(Staff staff){
        return("Hi, this is "+staff.getName()+" from "+staff.getRole());
        //return simple string for now
        //same thing, when we have user type class
        //can make it a login
    }
}
