package MainAccountingProcedure;

import Records.ProviderRecords;
import Records.ServiceRecords;
import Records.SummaryRecords;
import Users.Member;
import Records.MemberRecords;
import Users.Provider;
import Users.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class GenerateMemberReports {
    public static void updateSendToMembersFile(String report, String MemberName, String Date) throws IOException {
        // Write to SendToMembers.txt
        String filePath = STR."\{System.getProperty("user.home")}/Documents/ChocAn/MemberReports";
        new File(filePath).mkdirs();
        filePath = STR."\{System.getProperty("user.home")}/Documents/ChocAn/MemberReports/\{MemberName}_\{Date}.txt";
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

        for (Member member : MemberRecords.getInstance().members) {
            StringBuilder MemberReport = new StringBuilder(STR."""
            Member Name: \{member.Name}
            Member ID: \{member.ID}
            Member Address: \{member.Address}
            Member City: \{member.City}
            Member State: \{member.State}
            Member Zip: \{member.Zip}

            """);
            ArrayList<Service> services = new ArrayList<>();
            for (Service service : ServiceRecords.getInstance().services) {
                if(service.MemberID.equals(member.ID))
                {
                    Date weekToCheck;
                    Date weekAgoToCheck;
                    try {
                        weekToCheck = mdy.parse(service.ServiceDate);
                        weekAgoToCheck = mdy.parse(weekAgo);
                    }
                    catch (java.text.ParseException e) { throw new RuntimeException(e); }
                    if(weekToCheck.after(weekAgoToCheck)) services.add(service);
                }
            }
            // sort services by date
            services.sort(Comparator.comparing((Service s) -> s.ServiceDate));
            for (Service service : services) {
                String ProviderName = "";
                for (Provider provider : ProviderRecords.getInstance().providers) {
                    if (provider.ID.equals(service.ProviderID)) { ProviderName = provider.Name; break; }
                }
                MemberReport.append(STR."""
                \tService Date: \{service.ServiceDate}
                \tProvider Name: \{ProviderName}
                \tService Name: \{SummaryRecords.getInstance().summary.get(service.ServiceCode).getKey()}

                """);
            }
            if (!services.isEmpty()){
                try { updateSendToMembersFile(MemberReport.toString(), member.Name, todayDate); }
                catch (java.io.IOException e) { throw new RuntimeException(e); }
            }
        }
    }
}
