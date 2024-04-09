package Verification;

import Records.ManagerRecords;

public class VerifyManager implements Verify {
    public static void authenticate() {
        while (!Verify.authenticate(ManagerRecords.getInstance().managers)) {
            System.out.println("Invalid username or password. Please try again.");
        }
    }
}