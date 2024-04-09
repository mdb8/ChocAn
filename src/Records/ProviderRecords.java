package Records;

import Users.Provider;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProviderRecords {
    private static ProviderRecords all_providers = null;
    public ArrayList<Provider> providers;
    private ProviderRecords(ArrayList<Provider> p) { this.providers = p; }

    public static synchronized void initializeProviders() {
        if (all_providers == null) {
            ArrayList<Provider> records = new ArrayList<>();

            InputStream inputStream;
            String filePath = STR."\{System.getProperty("user.home")}/Documents/ChocAn/ProviderList.txt";
            if (new File(STR."\{filePath}").exists()) {
                try { inputStream = new FileInputStream(STR."\{filePath}"); }
                catch (FileNotFoundException e) { throw new RuntimeException(e); }
            }
            else inputStream = ManagerRecords.class.getResourceAsStream("/DataFiles/ProviderList.txt");

            if (inputStream != null) {
                Scanner fileScanner = new Scanner(inputStream);
                while (fileScanner.hasNextLine()) {
                    String[] data = fileScanner.nextLine().split(",");
                    if (data[0] != null) {
                        records.add(new Provider(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]));
                    }
                }
            }
            else System.out.println("File not found.");
            all_providers = new ProviderRecords(records);
        }
    }

    public static synchronized void addProvider(Provider provider) {
        if (all_providers == null) throw new IllegalStateException("Provider not initialized");
        all_providers.providers.add(provider);
    }
    public static synchronized void update() {
        if (all_providers == null) throw new IllegalStateException("Service not initialized");

        File file = new File(STR."\{System.getProperty("user.home")}/Documents/ChocAn/ProviderList.txt");
        try { file.createNewFile(); } catch (IOException e) { throw new RuntimeException(e); }
        java.io.FileWriter myWriter;
        try { myWriter = new java.io.FileWriter(file); } catch (IOException e) { throw new RuntimeException(e); }
        for (Provider provider : all_providers.providers) {
            try { myWriter.write(provider.toString() + "\n"); } catch (IOException e) { throw new RuntimeException(e); }
        }
        try { myWriter.close(); } catch (IOException e) { throw new RuntimeException(e); }
    }

    public static synchronized ProviderRecords getInstance() {
        if (all_providers == null) throw new IllegalStateException("Provider not initialized");
        return all_providers;
    }
}