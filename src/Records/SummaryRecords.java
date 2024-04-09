package Records;

import java.io.InputStream;
import java.util.*;

public class SummaryRecords {
    private static SummaryRecords all_summary = null;
    public HashMap<String, Map.Entry<String,String>> summary;
    private SummaryRecords(HashMap<String, Map.Entry<String, String>> p) { this.summary = p; }

    // Static method to create instance of Singleton class
    public static synchronized void initializeSummaries() {
        if (all_summary == null) {
            HashMap<String, Map.Entry<String,String>> records = new HashMap<>();
            InputStream inputStream = SummaryRecords.class.getResourceAsStream("/DataFiles/ProviderDirectory.txt");
            if (inputStream != null) {
                Scanner fileScanner = new Scanner(inputStream);
                while (fileScanner.hasNextLine()) {
                    String[] data = fileScanner.nextLine().split(",");
                    if (data[0] != null) {
                        records.put(data[0], new AbstractMap.SimpleEntry<>(data[1],data[2]));
                    }
                }
            }
            else System.out.println("File not found.");
            all_summary = new SummaryRecords(records);
        }
    }

    public static synchronized SummaryRecords getInstance()
    {
        if (all_summary == null) throw new IllegalStateException("Record not initialized");
        return all_summary;
    }
}