package Verification;

import Records.ProviderRecords;

public class VerifyProvider implements Verify {
    public static void authenticate() {
        while (!Verify.authenticate(ProviderRecords.getInstance().providers)) {
            System.out.println("Invalid username or password. Please try again.");
        }
    }
}