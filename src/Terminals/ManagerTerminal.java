package Terminals;

import MainAccountingProcedure.GenerateMemberReports;
import MainAccountingProcedure.GenerateProviderReports;
import MainAccountingProcedure.GenerateSummaryReport;
import Verification.VerifyManager;

public class ManagerTerminal implements Terminal {
    public void startTerminal() {
        VerifyManager.authenticate();
        managerTerminal();
    }

    private void managerTerminal(){
        String[] options = {"Generate Provider Report", "Generate Member Report", "Generate Summary Report"};
        Runnable[] actions = {GenerateProviderReports::generateReports, GenerateMemberReports::generateReports, GenerateSummaryReport::generateReports};
        Terminal.terminal(options, actions);
    }
}
