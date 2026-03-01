package dcoms_assignment;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LeaveService {

    StringBuilder sb = new StringBuilder();
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

    public void ApplyLeave(Staff staff, LocalDate date1, LocalDate date2, String reason) {
        String newId = generateLeaveId();
        Leaves NewLeave = new Leaves(
                "",
                staff.getStaffID(),
                date1,
                date2,
                reason,
                "PENDING"
        );
        NewLeave.setLeaveId(newId);
        leaveMap.put(newId, NewLeave);
        saveToFile();
    }

    public String ApproveLeave(String leaveId) {
        loadFromFile();
        Leaves leave = leaveMap.get(leaveId);
        if (leave != null) {
            leave.setStatus("APPROVED");
            saveToFile();
            return "Leave ID: " + leaveId + " has been approved!";
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
            return "Leave ID: " + leaveId + " has been rejected!";
        } else {
            return "Leave not found";
        }
    }

    public String getAllLeaves() {

        loadFromFile();

        if (leaveMap.isEmpty()) {
            return "No leave records found.";
        }
        for (Leaves leave : leaveMap.values()) {
            sb.append("----------------------------\n");
            sb.append("Leave ID: ").append(leave.getLeaveId()).append("\n");
            sb.append("Staff ID: ").append(leave.getStaffId()).append("\n");
            sb.append("Start Date: ").append(leave.getStartDate()).append("\n");
            sb.append("End Date: ").append(leave.getEndDate()).append("\n");
            sb.append("Reason: ").append(leave.getReason()).append("\n");
            sb.append("Status: ").append(leave.getStatus()).append("\n");
        }
        sb.append("\nDo you want to:");
        sb.append("\n1. Approve Leave");
        sb.append("\n2. Reject Leave");
        sb.append("\n3. Delete Leave");
        sb.append("\n4. Exit");
        sb.append("\nEnter choice: ");
        return sb.toString();
    }

    public String getLeavesByStaffId(String staffId) {

        loadFromFile();

        if (leaveMap.isEmpty()) {
            return "No leave records found.";
        }

        StringBuilder sb = new StringBuilder();
        boolean found = false;

        for (Leaves leave : leaveMap.values()) {

            if (leave.getStaffId().equals(staffId)) {
                found = true;
                sb.append("----------------------------\n");
                sb.append("Leave ID: ").append(leave.getLeaveId()).append("\n");
                sb.append("Start Date: ").append(leave.getStartDate()).append("\n");
                sb.append("End Date: ").append(leave.getEndDate()).append("\n");
                sb.append("Reason: ").append(leave.getReason()).append("\n");
                sb.append("Status: ").append(leave.getStatus()).append("\n");
            }
        }

        if (!found) {
            return "No record found for StaffID: " + staffId;
        }

        return sb.toString();
    }

    public String DeleteLeave(String leaveId) {

        loadFromFile();

        if (!leaveMap.containsKey(leaveId)) {
            return "Leave ID not found.";
        }

        leaveMap.remove(leaveId);
        saveToFile();

        return "LeaveID: " + leaveId + " deleted successfully";
    }

}
