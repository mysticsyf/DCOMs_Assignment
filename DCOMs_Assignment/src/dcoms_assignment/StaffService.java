package dcoms_assignment;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StaffService {
    private Map<String, Staff> StaffMap;
    private final String FileName = "Staff.ser";

    public StaffService() {
        StaffMap = new HashMap<>();
        LoadfromFile();
    }
    
    private void LoadfromFile(){
        File file = new File(FileName);
        
        if(!file.exists()){
            System.out.println("no file bruh");
            return;
        }
        
        try{
            FileInputStream FileIn = new FileInputStream(FileName);
            ObjectInputStream In = new ObjectInputStream(FileIn);
            StaffMap = (HashMap<String, Staff>) In.readObject();
            In.close();
            FileIn.close();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    private void SavetoFile(){       
        try{
            FileOutputStream FileOut = new FileOutputStream(FileName);
            ObjectOutputStream Out = new ObjectOutputStream(FileOut);
            Out.writeObject(StaffMap);
            Out.close();
            FileOut.close();
            System.out.println("Book serialized and saved to file");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public Staff CheckLogin(String StaffID, String Password){
        Staff staff = StaffMap.get(StaffID);
        
        if(staff == null){
            return null;
        }
        
        if(staff.getPassword().equals(Password)){
            return staff;
        }else{
            return null;
        }
    }
    
    //add more find staff stuff
}
