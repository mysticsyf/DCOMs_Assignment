package dcoms_assignment;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LeaveService {
    private Map<String, Leaves> leaveMap = new HashMap<>();
    private final String FileName = "leaves.ser";
    
    private void loadFromFile(){
        File file = new File(FileName);
        
        if(!file.exists()){
            System.out.println("no file found");
            return;
        }
        
        try{
            FileInputStream FileIn = new FileInputStream(FileName);
            ObjectInputStream In = new ObjectInputStream(FileIn);
            leaveMap = (HashMap<String, Leaves>) In.readObject();
            In.close();
            FileIn.close();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    private void saveToFile(){
        try{
            FileOutputStream FileOut = new FileOutputStream(FileName);
            ObjectOutputStream Out = new ObjectOutputStream(FileOut);
            Out.writeObject(leaveMap);
            Out.close();
            FileOut.close();
            System.out.println("Saved changes made to "+FileName);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void ApplyLeave(Leaves leave){
        leaveMap.put(leave.getLeaveId(), leave);
        saveToFile();
    }
    
    public void ApproveLeave(String leaveId){
        Leaves leave = leaveMap.get(leaveId);
        if(leave != null){
            leave.setStatus("APPROVED");
            saveToFile();
        }else{
            System.out.println("Leave not found.");
        }
    }
}