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

            Login login = (Login) reg.lookup("Login");

            Staff CurrentUser = login.Login("HR01", "1234");
            if (CurrentUser != null) {
                if (CurrentUser.getRole().equals("STAFF")) {
                    ApplyLeave al = (ApplyLeave) reg.lookup("ApplyLeave");
                    Scanner sc = new Scanner(System.in);
                    int choice;
                    do {
                        System.out.println("\n===== STAFF MENU =====");
                        System.out.println("1. Apply Leave");
                        System.out.println("2. Exit");
                        System.out.print("Enter choice: ");
                        choice = sc.nextInt();
                        sc.nextLine();
                        switch (choice) {
                            case 1:
                                System.out.println("Enter start date (YYYY-MM-DD): ");
                                LocalDate start = LocalDate.parse(sc.nextLine());

                                System.out.println("Enter end date (YYYY-MM-DD): ");
                                LocalDate end = LocalDate.parse(sc.nextLine());

                                System.out.println("Enter leave reason: ");
                                String reason = sc.nextLine();

                                Leaves leave = new Leaves("", CurrentUser.getStaffID(), start, end, reason, "PENDING"); //leave id auto increment

                                String result = al.applyLeave(leave);
                                System.out.println(result);
                                break;
                            case 2:
                                System.out.println("Exiting...");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    } while (choice != 2);

                } else if (CurrentUser.getRole().equals("HR")) {
                    ApplyLeave al = (ApplyLeave) reg.lookup("ApplyLeave");
                    Scanner sc = new Scanner(System.in);

                    int choice;
                    do {
                        System.out.println("\n===== HR MENU =====");
                        System.out.println("1. View Leave Applications");
                        System.out.println("2. Exit");
                        System.out.print("Enter choice: ");
                        choice = sc.nextInt();
                        sc.nextLine();
                        if (choice == 1) {

                            Map<String, Leaves> leaves = al.getAllLeaves();

                            if (leaves.isEmpty()) {
                                System.out.println("No record found.");
                            } else {
                                System.out.println("\n===== All leave Applications =====");
                                for (Leaves leave : leaves.values()) {
                                    System.out.println("----------------------------");
                                    System.out.println("Leave ID: " + leave.getLeaveId());
                                    System.out.println("Staff ID: " + leave.getStaffId());
                                    System.out.println("Start Date: " + leave.getStartDate());
                                    System.out.println("End Date: " + leave.getEndDate());
                                    System.out.println("Reason: " + leave.getReason());
                                    System.out.println("Status: " + leave.getStatus());
                                }

                                System.out.println("\nDo you want to:");
                                System.out.println("1. Approve Leave");
                                System.out.println("2. Reject Leave");
                                System.out.println("3. Exit");
                                System.out.print("Enter choice: ");

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

                                    System.out.println("Returning to HR menu");
                                } else {
                                    System.out.println("Invalid choice.");
                                }
                            }
                        }
                    } while (choice != 2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
