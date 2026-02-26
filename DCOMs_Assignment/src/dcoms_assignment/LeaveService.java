package dcoms_assignment;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LeaveService {

    private Map<String, Leaves> leaveMap = new HashMap<>();
    private final String FileName = "leaves.ser";

    private void loadFromFile() {
        File file = new File(FileName);

        if (!file.exists()) {
            System.out.println("no file found");
            return;
        }

        try {
            FileInputStream FileIn = new FileInputStream(FileName);
            ObjectInputStream In = new ObjectInputStream(FileIn);
            leaveMap = (HashMap<String, Leaves>) In.readObject();
            leaveCounter = leaveMap.size() + 1;
            In.close();
            FileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            FileOutputStream FileOut = new FileOutputStream(FileName);
            ObjectOutputStream Out = new ObjectOutputStream(FileOut);
            Out.writeObject(leaveMap);
            Out.close();
            FileOut.close();
            System.out.println("Saved changes made to " + FileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int leaveCounter = 1;

    private String generateLeaveId() {
        return String.format("L%02d", leaveCounter++);
    }

    public String ApplyLeave(Leaves leave) {
        String newId = generateLeaveId();
        leave.setLeaveId(newId);
        leaveMap.put(newId, leave);
        saveToFile();
        return newId;
    }

    public String ApproveLeave(String leaveId) {
        loadFromFile();
        Leaves leave = leaveMap.get(leaveId);
        if (leave != null) {
            leave.setStatus("APPROVED");
            saveToFile();
            return "Leave ID: " +leaveId+" has been approved!";
        } else {
            return "Leave not found";
        }
    }

    public String RejectLeave(String leaveId) {
        loadFromFile();
        Leaves leave = leaveMap.get(leaveId);
        if (leave != null) {
            leave.setStatus("REJECTED");
            saveToFile();
            return "Leave ID: " +leaveId+" has been rejected!";
        } else {
            return "Leave not found";
        }
    }
    
    public Map<String, Leaves> getAllLeaves() {
        loadFromFile();
        return leaveMap;
    }
}
