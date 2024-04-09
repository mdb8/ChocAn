package Verification;

import Records.OperatorRecords;

public class VerifyOperator implements Verify {
    public static void authenticate() {
        while (!Verify.authenticate(OperatorRecords.getInstance().operators)) {
            System.out.println("Invalid username or password. Please try again.");
        }
    }
}