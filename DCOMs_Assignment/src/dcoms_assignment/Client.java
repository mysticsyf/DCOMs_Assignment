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
                        System.out.println("2. View your Leave");

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
                        System.out.println("2. Exit");
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
                        }

                    } while (choice != 2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
