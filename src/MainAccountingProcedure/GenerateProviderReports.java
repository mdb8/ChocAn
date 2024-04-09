package MainAccountingProcedure;

import Records.MemberRecords;
import Records.ProviderRecords;
import Records.ServiceRecords;
import Records.SummaryRecords;
import Users.Member;
import Users.Provider;
import Users.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateProviderReports {
    public static void updateSendToProvidersFile(String report, String ProviderName, String Date) throws IOException {
        // Write to SendToMembers.txt
        String filePath = STR."\{System.getProperty("user.home")}/Documents/ChocAn/ProviderReports";
        new File(filePath).mkdirs();
        filePath = STR."\{System.getProperty("user.home")}/Documents/ChocAn/ProviderReports/\{ProviderName}_\{Date}.txt";
        File file = new File(filePath);
        file.createNewFile();
        try {
            java.io.FileWriter myWriter = new java.io.FileWriter(file);
            myWriter.write(report);
            myWriter.close();
        }
        catch (java.io.IOException e) { throw new RuntimeException(e); }
    }
    public static void generateReports() {
        SimpleDateFormat mdy = new SimpleDateFormat("MM-dd-yyyy");
        Date today = new Date();
        String todayDate = mdy.format(today);

        String weekAgo = mdy.format(new Date(today.getTime() - 7 * 24 * 3600 * 1000));

        for (Provider provider : ProviderRecords.getInstance().providers) {
            String SendToProviders = "";
            SendToProviders += STR."""
            Provider Name: \{provider.Name}
            Provider ID: \{provider.ID}
            Provider Address: \{provider.Address}
            Provider City: \{provider.City}
            Provider State: \{provider.State}
            Provider Zip: \{provider.Zip}

            Services Provided This Week:
            """;

            int count = 0;
            float totalFee = 0;
            // Generate report for provider
            for (Service service : ServiceRecords.getInstance().services) {
                if(service.ProviderID.equals(provider.ID))
                {
                    Date weekToCheck;
                    Date weekAgoToCheck;
                    try {
                        weekToCheck = mdy.parse(service.ServiceDate);
                        weekAgoToCheck = mdy.parse(weekAgo);
                    }
                    catch (java.text.ParseException e) { throw new RuntimeException(e); }

                    if (weekToCheck.after(weekAgoToCheck)) {
                        count++;
                        float fee = Float.parseFloat(SummaryRecords.getInstance().summary.get(service.ServiceCode).getValue());
                        totalFee += fee;

                        // Find the member name by ID
                        String MemberName = "";
                        for (Member member : MemberRecords.getInstance().members) {
                            if (member.ID.equals(service.MemberID)) {
                                MemberName = member.Name;
                                break;
                            }
                        }

                        SendToProviders += STR."""
                    \tDate of Service: \{service.ServiceDate}
                    \tDate and Time at Entry: \{service.EntryDate}
                    \tMember Name: \{MemberName}
                    \tMember ID: \{service.MemberID}
                    \tService Code: \{service.ServiceCode}
                    \tFee to be Paid: $\{String.format("%.2f", fee)}

                    """;
                    }
                }
            }
            SendToProviders += STR."Total number of consultations: \{String.format("%3d",count)}\n";
            SendToProviders += STR."Total fee to be paid this week: $\{String.format("%5.2f",totalFee)}\n";

            if (count > 0){
                try { updateSendToProvidersFile(SendToProviders, provider.Name, todayDate); }
                catch (java.io.IOException e) { throw new RuntimeException(e); }
            }
        }
    }
}