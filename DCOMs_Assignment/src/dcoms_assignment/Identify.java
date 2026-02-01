package dcoms_assignment;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Identify extends Remote{
    String Identify() throws RemoteException;
    //we make it no parameter for now
    //once we have diff user type classes, we can use them to make a login action/feature
    //this is a very inefficient example, will improve later with string parameter for diff user, then class
}
