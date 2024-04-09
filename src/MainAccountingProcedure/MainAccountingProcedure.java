package MainAccountingProcedure;


public class MainAccountingProcedure {
    public static void runMAP() {
        GenerateProviderReports.generateReports();
        GenerateMemberReports.generateReports();
        GenerateSummaryReport.generateReports();
        WriteEFTRecord.updateEFTRecordFile();
        System.out.println("Accounting procedure complete.");
    }
}
