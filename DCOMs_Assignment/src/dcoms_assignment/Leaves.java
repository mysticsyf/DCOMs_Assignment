package dcoms_assignment;
import java.io.Serializable;
import java.time.LocalDate;

public class Leaves implements Serializable{
    private static final long serialVersionUID = 1L;

    private String leaveId;
    private String staffId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String status;

    public Leaves(String leaveId, String staffId,LocalDate startDate, LocalDate endDate,String reason, String status) {
        this.leaveId = leaveId;
        this.staffId = staffId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    public String getLeaveId() {
        return leaveId;
    }
    
    public String getStaffId() {
        return staffId;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public String getReason() {
        return reason;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
