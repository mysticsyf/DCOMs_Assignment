package dcoms_assignment;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StaffService {
    
    StringBuilder sb = new StringBuilder();
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
            for(Staff staff : StaffMap.values()){
                if(staff.getRole() == "STAFF"){
                    StaffCounter += 1;
                }else if(staff.getRole() == "HR"){
                    HRCounter += 1;
                }
            }
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
            System.out.println("Saved changes made to "+FileName);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    private int StaffCounter = 1;
    private int HRCounter = 1;
    
    private String generateStaffId(Staff staff) {
        if(staff.getRole() == "STAFF"){
            return String.format("ST%02d", StaffCounter++);
        }else if(staff.getRole() == "HR"){
            return String.format("HR%02d", HRCounter++);
        }else{
            return null;
        }
    }
    
    public void ReduceStaffLeave(String StaffID){
        LoadfromFile();
        Staff ChosenStaff = StaffMap.get(StaffID);
        ChosenStaff.ReduceLeave();
        SavetoFile();
    }
    
    public void AddStaff(Staff staff){
        String newId = generateStaffId(staff);
        staff.setStaffID(newId);
        StaffMap.put(newId, staff);
        SavetoFile();
    }
    
    public void AddStaff(String name, String role, int salary){
        Staff NewStaff = new Staff("", name, role, "1234", salary);
        String newId = generateStaffId(NewStaff);
        NewStaff.setStaffID(newId);
        StaffMap.put(newId, NewStaff);
        SavetoFile();
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
    
    public String ViewProfile(Staff staff){
        Staff StaffShow = StaffMap.get(staff.getStaffID());
        return StaffShow.toString();
    }
    
    public String UpdateName(String ChosenID, String newName){
        LoadfromFile();
        Staff ChosenStaff = StaffMap.get(ChosenID);
        ChosenStaff.setName(newName);
        SavetoFile();
        return "Name: " + newName + "has been updated";
    }
    
    public String UpdateRole(String ChosenID, String newRole){
        LoadfromFile();
        Staff ChosenStaff = StaffMap.get(ChosenID);
        ChosenStaff.setRole(newRole);
        SavetoFile();
        return "Role: " + newRole + "has been updated";
    }
    
    public String UpdatePassword(String ChosenID, String newPassword){
        LoadfromFile();
        Staff ChosenStaff = StaffMap.get(ChosenID);
        ChosenStaff.setPassword(newPassword);
        SavetoFile();
        return "Password: " + newPassword + "has been updated";
    }
    
    public String UpdateSalary(String ChosenID, int newSalary){
        LoadfromFile();
        Staff ChosenStaff = StaffMap.get(ChosenID);
        ChosenStaff.setSalary(newSalary);
        SavetoFile();
        return "Salary: " + newSalary + "has been updated";
    }
    
    public String UpdateLeaves(String ChosenID, int newRemLeave){
        LoadfromFile();
        Staff ChosenStaff = StaffMap.get(ChosenID);
        ChosenStaff.setRemainingLeaves(newRemLeave);
        SavetoFile();
        return "Remaining Leaves: " + newRemLeave + "has been updated";
    }
    
    public String getAllStaff() {

        LoadfromFile();

        if (StaffMap.isEmpty()) {
            return "No staff records found.";
        }
        for (Staff staff : StaffMap.values()) {
            sb.append("----------------------------\n");
            sb.append("Staff ID: ").append(staff.getStaffID()).append("\n");
            sb.append("Name: ").append(staff.getName()).append("\n");
            sb.append("Role: ").append(staff.getRole()).append("\n");
            sb.append("Password: ").append(staff.getPassword()).append("\n");
            sb.append("Salary: ").append(staff.getSalary()).append("\n");
            sb.append("Remaining Leaves").append(staff.getRemainingLeaves()).append("\n");
        }
        sb.append("\nSelect StaffID: ");
        return sb.toString();
    }
}
