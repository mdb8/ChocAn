package MainAccountingProcedure;

import Records.ProviderRecords;
import Records.ServiceRecords;
import Records.SummaryRecords;
import Users.Provider;
import Users.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateSummaryReport {
    public static void SendToSummaryReport(String report, String Date) throws IOException {
        // Write to SendToSummaryReport.txt
        String filePath = STR."\{System.getProperty("user.home")}/Documents/ChocAn/SummaryReport";
        new File(filePath).mkdirs();
        filePath = STR."\{System.getProperty("user.home")}/Documents/ChocAn/SummaryReport/SummaryReport_\{Date}.txt";
        File file = new File(filePath);
        file.createNewFile();

        try {
            java.io.FileWriter myWriter = new java.io.FileWriter(file);
            myWriter.write(report);
            myWriter.close();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateReports() {
        SimpleDateFormat mdy = new SimpleDateFormat("MM-dd-yyyy");
        Date today = new Date();
        String todayDate = mdy.format(today);

        String weekAgo = mdy.format(new Date(today.getTime() - 7 * 24 * 3600 * 1000));

        String ProvidersToPay = "";
        int totalProviders = 0;
        int totalConsultations = 0;
        float overallFee = 0;

        for (Provider provider : ProviderRecords.getInstance().providers) {

            int count = 0;
            float totalFee = 0;
            // Generate report for provider
            for (Service service : ServiceRecords.getInstance().services) {
                if (service.ProviderID.equals(provider.ID) )
                {
                    Date weekToCheck;
                    Date weekAgoToCheck;
                    try {
                        weekToCheck = mdy.parse(service.ServiceDate);
                        weekAgoToCheck = mdy.parse(weekAgo);
                    }
                    catch (java.text.ParseException e) { throw new RuntimeException(e); }
                    if(weekToCheck.after(weekAgoToCheck)) {
                        count++;
                        float fee = Float.parseFloat(SummaryRecords.getInstance().summary.get(service.ServiceCode).getValue());
                        totalFee += fee;
                        totalConsultations++;
                        overallFee += fee;
                    }
                }
            }
            if (count > 0) {
                totalProviders++;
                ProvidersToPay += STR."Provider Name: \{provider.Name}\n";
                ProvidersToPay += STR."Provider ID: \{provider.ID}\n";
                ProvidersToPay += STR."Total number of consultations: \{String.format("%3d", count)}\n";
                ProvidersToPay += STR."Total fee to be paid this week: $\{String.format("%5.2f", totalFee)}\n\n";
            }
        }
        ProvidersToPay += STR."Total number of providers who provided services: \{String.format("%3d", totalProviders)}\n";
        ProvidersToPay += STR."Total number of consultations: \{String.format("%3d", totalConsultations)}\n";
        ProvidersToPay += STR."Overall fee total: $\{String.format("%7.2f", overallFee)}\n";

        try {
            SendToSummaryReport(ProvidersToPay, todayDate);
        }catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
