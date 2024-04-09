package ProviderActions;

import Users.Member;
import java.util.Scanner;

public class Lookup {

    public static void lookupMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the member's number: ");
        String memberNumber = scanner.nextLine();

        Member member = ValidateMembers.validateMember(memberNumber);
        if (member != null) {
            System.out.println("Member Found: ");
            System.out.println(STR."Name: \{member.Name}");
            System.out.println(STR."ID: \{member.ID}");
            System.out.println(STR."Address: \{member.Address}");
            System.out.println(STR."City: \{member.City}");
            System.out.println(STR."State: \{member.State}");
            System.out.println(STR."Zip: \{member.Zip}");
            System.out.println(STR."Email: \{member.Email}");
            System.out.println();
        }
        else System.out.println("Member number not found.");
    }
}

