package dcoms_assignment;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;

public class Staff implements Serializable{
    private static final long serialVersionUID = 1L;
    String StaffID;
    String Name;
    String Role;
    String Password;
    int RemainingLeaves;
    int Salary;
    LocalDate UpcomingLeave;

    public Staff(String StaffID, String Name, String Role, String Password, int Salary) {
        this.StaffID = StaffID;
        this.Name = Name;
        this.Role = Role;
        this.Password = Password;
        this.RemainingLeaves = 5;
        this.Salary = Salary;
    }

    public String getName() {
        return Name;
    }

    public String getRole() {
        return Role;
    }

    public String getStaffID() {
        return StaffID;
    }

    public String getPassword() {
        return Password;
    }

    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setRemainingLeaves(int RemainingLeaves) {
        this.RemainingLeaves = RemainingLeaves;
    }

    public void setSalary(int Salary) {
        this.Salary = Salary;
    }
    
    public void ReduceLeave(){
        this.RemainingLeaves -= 1;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getRemainingLeaves() {
        return RemainingLeaves;
    }

    public int getSalary() {
        return Salary;
    }

    @Override
    public String toString() {
        return "Staff{" + "StaffID=" + StaffID + ", \nName=" + Name + 
                ", \nRole=" + Role + ", \nPassword=" + Password + 
                ", \nRemainingLeaves=" + RemainingLeaves + 
                ", \nSalary=" + Salary + ", \nUpcomingLeave=" + UpcomingLeave + '}';
    }
    
    
   
}
