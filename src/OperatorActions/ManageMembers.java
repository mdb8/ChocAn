package OperatorActions;

import Records.MemberRecords;
import Users.Member;
import java.util.*;

public class ManageMembers {
    public static void addMember(){
        MemberRecords.addMember(getMemberData());
        MemberRecords.update();
    }
    public static void deleteMember(){
        MemberRecords.getInstance().members.remove(getMember());
        MemberRecords.update();
    }
    public static void manageMember(){
        Member member = getMember();
        System.out.println("Enter the field you would like to change: ");
        System.out.println("1. Name");
        System.out.println("2. ID");
        System.out.println("3. Address");
        System.out.println("4. City");
        System.out.println("5. State");
        System.out.println("6. Zip Code");
        System.out.println("7. Email");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter the new name: ");
                member.Name = scanner.nextLine();
                break;
            case "2":
                System.out.println("Enter the new ID: ");
                member.ID = scanner.nextLine();
                break;
            case "3":
                System.out.println("Enter the new address: ");
                member.Address = scanner.nextLine();
                break;
            case "4":
                System.out.println("Enter the new city: ");
                member.City = scanner.nextLine();
                break;
            case "5":
                System.out.println("Enter the new state: ");
                member.State = scanner.nextLine();
                break;
            case "6":
                System.out.println("Enter the new zip code: ");
                member.Zip = scanner.nextLine();
                break;
            case "7":
                System.out.println("Enter the new email: ");
                member.Email = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice.");
        }
        MemberRecords.update();
    }

    public static Member getMember(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the member's id: ");
            String IDToFind = scanner.nextLine();
            for (Member member : MemberRecords.getInstance().members) {
                if (member.ID.equals(IDToFind)) {
                    return member;
                }
            }
            System.out.println("Member not found. Try again.");
        }
    }

    public static Member getMemberData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the member's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the member's ID: ");
        String id = scanner.nextLine();
        System.out.println("Enter the member's address: ");
        String address = scanner.nextLine();
        System.out.println("Enter the member's city: ");
        String city = scanner.nextLine();
        System.out.println("Enter the member's state: ");
        String state = scanner.nextLine();
        System.out.println("Enter the member's zip code: ");
        String zip = scanner.nextLine();
        System.out.println("Enter the member's email: ");
        String email = scanner.nextLine();

        return new Member(name, id, address, city, state, zip, email);
    }
}
