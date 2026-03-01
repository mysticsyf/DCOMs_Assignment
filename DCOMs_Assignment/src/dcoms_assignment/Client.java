package dcoms_assignment;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);

            StaffAction StaffActions = (StaffAction) reg.lookup("StaffAction");

            Staff CurrentUser = StaffActions.Login("HR01", "1234");
            if (CurrentUser != null) {
                if (CurrentUser.getRole().equals("STAFF")) {
                    ApplyLeave al = (ApplyLeave) reg.lookup("ApplyLeave");
                    Scanner sc = new Scanner(System.in);
                    int choice;
                    do {
                        System.out.println("\n===== STAFF MENU =====");
                        System.out.println("1. Apply Leave");
                        System.out.println("2. View your Leave");
                        System.out.println("3. View your Profile");
                        

                        System.out.print("Enter choice: ");
                        choice = sc.nextInt();
                        sc.nextLine();
                        if (choice == 1) {
                            System.out.println("Enter start date (YYYY-MM-DD): ");
                            LocalDate start = LocalDate.parse(sc.nextLine());

                            System.out.println("Enter end date (YYYY-MM-DD): ");
                            LocalDate end = LocalDate.parse(sc.nextLine());

                            System.out.println("Enter leave reason: ");
                            String reason = sc.nextLine();

                            al.applyLeave(CurrentUser, start, end, reason);

                        } else if (choice == 2) {
                            String result = al.getLeavesByStaffId(CurrentUser.getStaffID());
                            System.out.println(result);
                            
                        } else if (choice == 3){
                            String result = StaffActions.ViewProfile(CurrentUser);
                            System.out.println(result);
                      
                        } else {
                            System.out.println("Invalid choice.");
                        }

                    } while (choice != 3);

                } else if (CurrentUser.getRole().equals("HR")) {
                    ApplyLeave al = (ApplyLeave) reg.lookup("ApplyLeave");
                    Scanner sc = new Scanner(System.in);

                    int choice;
                    do {
                        System.out.println("\n===== HR MENU =====");
                        System.out.println("1. View Leave Applications");
                        System.out.println("2. Update Staff Profile");
                        System.out.println("3. Exit");
                        System.out.print("Enter choice: ");
                        choice = sc.nextInt();
                        sc.nextLine();
                        if (choice == 1) {

                            String leaveDetails = al.getAllLeaves();

                            System.out.println(leaveDetails);
                            int choice2 = sc.nextInt();
                            sc.nextLine();
                            if (choice2 == 1) {

                                System.out.print("Enter Leave ID to approve: ");
                                String leaveId = sc.nextLine();

                                String message = al.ApproveLeave(leaveId);
                                System.out.println(message);

                            } else if (choice2 == 2) {

                                System.out.print("Enter Leave ID to reject: ");
                                String leaveId = sc.nextLine();

                                String message = al.RejectLeave(leaveId);
                                System.out.println(message);

                            } else if (choice2 == 3) {

                                System.out.print("Enter Leave ID to delete: ");
                                String leaveId = sc.nextLine();
                                String result = al.DeleteLeave(leaveId);
                                System.out.println(result);

                            } else if (choice2 == 4) {
                                System.out.println("Returning to HR menu");
                            } else {
                                System.out.println("Invalid choice");
                            }
                        }else if (choice == 2){
                            String StaffDetails = StaffActions.GetAllStaff();
                            System.out.println(StaffDetails);
                            
                            String ChosenID = sc.nextLine();
                            System.out.println("\n1. Name" + "\n2. Role" + 
                                    "\n3. Password" + "\n4. Salary" + 
                                    "\n5. Remaining Leaves");
                            System.out.println("Select attribute to update: ");
                            int choice2 = sc.nextInt();
                            
                            if(choice2 == 1){
                                System.out.println("Enter the new Name: ");
                                String newName = sc.nextLine();
                                
                                StaffActions.UpdateName(ChosenID, newName);
                            }else if (choice2 == 2){
                                System.out.println("Enter the new Role: ");
                                String newRole = sc.nextLine();
                                
                                StaffActions.UpdateRole(ChosenID, newRole);
                            }else if (choice2 == 3){
                                System.out.println("Enter the new Password: ");
                                String newPassword = sc.nextLine();
                                
                                StaffActions.UpdatePassword(ChosenID, newPassword);
                            }else if (choice2 == 4){
                                System.out.println("Enter the new Salary: ");
                                int newSalary = sc.nextInt();
                                
                                StaffActions.UpdateSalary(ChosenID, newSalary);
                            }else if (choice2 == 5){
                                System.out.println("Enter the new Remaining Leaves: ");
                                int newRemLeave = sc.nextInt();
                                
                                StaffActions.UpdateLeaves(ChosenID, newRemLeave);
                            }                  
                        }

                    } while (choice != 3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
