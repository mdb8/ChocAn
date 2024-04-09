package ProviderActions;

import Records.ServiceRecords;
import Records.SummaryRecords;
import Users.ActiveProvider;
import Users.Member;
import Users.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class BillMembers {
    public static void billMember() {
        Scanner scanner = new Scanner(System.in);
        boolean validService = false;

        System.out.print("Please enter the member's number: ");
        String memberNumber = scanner.nextLine();
        Member member = ValidateMembers.validateMember(memberNumber);
        while (member == null) {
            System.out.println("Member not found. Please enter the member's number: ");
            memberNumber = scanner.nextLine();
            member = ValidateMembers.validateMember(memberNumber);
        }
        String MemberID = member.ID;

        System.out.println("Member found. Please enter the service code: ");
        String serviceCode = scanner.nextLine();
        validService = validateServiceCode(serviceCode);
        while(!validService){
            System.out.println("Service code not valid. Please try again: ");
            serviceCode = scanner.nextLine();
            validService = validateServiceCode(serviceCode);
        }
        String ServiceCode = serviceCode;

        System.out.println("Please enter any comments regarding this service: ");
        String Comment = scanner.nextLine();

        System.out.println("Please enter the date of service: ");
        String date = scanner.nextLine();
        while (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
            System.out.println("Invalid date format. Please enter the date of service (MM-DD-YYYY): ");
            date = scanner.nextLine();
        }
        String ServiceDate = date;

        SimpleDateFormat mdy = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date today = new Date();
        String EntryDate = mdy.format(today);

        String ProviderID = ActiveProvider.getInstance().number;


        String amount = SummaryRecords.getInstance().summary.get(serviceCode).getValue();
        boolean paymentMade = RequestPayment.requestPayment(amount);


        if (paymentMade) {
            System.out.println("Payment confirmed.");
            System.out.println(STR."Member billed: $\{amount} for service \{ServiceCode}.");
            ServiceRecords.addService(new Service(ServiceDate, EntryDate, ProviderID, MemberID, ServiceCode, Comment));
            try{ ServiceRecords.update(); }
            catch (IOException e) { System.out.println("Error updating service records."); }
            System.out.println();
        } else {
            System.out.println("Payment not confirmed.");
            System.out.println();
        }
    }

    public static boolean validateServiceCode(String serviceCode) {
        for (Map.Entry<String, Map.Entry<String, String>> entry : SummaryRecords.getInstance().summary.entrySet()) {
            if (entry.getKey().equals(serviceCode)) {
                System.out.println(STR."Please enter 1 to confirm service provided: \{entry.getValue().getKey()}");

                Scanner scanner = new Scanner(System.in);
                String confirmation = scanner.nextLine();
                return confirmation.equals("1");
            }
        }
        return false;
    }
}
