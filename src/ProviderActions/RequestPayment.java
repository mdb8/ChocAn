package ProviderActions;
import java.util.Scanner;

public class RequestPayment {
    public static boolean requestPayment(String amount) {
        int amountInt = Integer.parseInt(amount);
        Scanner scanner = new Scanner(System.in);
        System.out.println(STR."Payment requested: $\{amountInt}");
        System.out.println("Please have the member type 1 to confirm payment.");
        String confirmation = scanner.nextLine();
        return confirmation.equals("1");
    }
}
