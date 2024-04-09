package Terminals;

import Records.ProviderRecords;
import Users.ActiveProvider;
import Users.Provider;
import Verification.VerifyProvider;
import java.util.Scanner;

public class ProviderTerminal implements Terminal{
    public void startTerminal() {
        VerifyProvider.authenticate();

        Scanner scanner = new Scanner(System.in);
        boolean match = false;
        String providerNumber = "";
        while (!match) {
            System.out.println("Enter your provider number: ");
            providerNumber = scanner.nextLine();
            for (Provider user : ProviderRecords.getInstance().providers) {
                if (user.ID.equals(providerNumber)) { match = true; break; }
            }
            if (!match) System.out.println("Incorrect provider number.");
        }
        ActiveProvider.login(providerNumber);
        providerTerminal();
    }

    private void providerTerminal(){
        String[] options = {"Lookup Member", "Bill Member", "View Service Directory"};
        Runnable[] actions = {ProviderActions.Lookup::lookupMember, ProviderActions.BillMembers::billMember, ProviderActions.ProviderDirectory::viewDirectory};
        Terminal.terminal(options, actions);
    }
}
