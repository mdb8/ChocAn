package Records;

import Users.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiceRecords {
    private static ServiceRecords all_services = null;
    public ArrayList<Service> services;
    private ServiceRecords(ArrayList<Service> s) { this.services = s; }

    public static synchronized void initializeServices() {
        if (all_services == null) {
            ArrayList<Service> records = new ArrayList<>();

            InputStream inputStream;
            String filePath = STR."\{System.getProperty("user.home")}/Documents/ChocAn/ServiceList.txt";
            if (new File(STR."\{filePath}").exists()) {
                try { inputStream = new FileInputStream(STR."\{filePath}"); }
                catch (FileNotFoundException e) { throw new RuntimeException(e); }
            }
            else inputStream = ManagerRecords.class.getResourceAsStream("/DataFiles/ServiceList.txt");

            if (inputStream != null) {
                Scanner fileScanner = new Scanner(inputStream);
                while (fileScanner.hasNextLine()) {
                    String[] data = fileScanner.nextLine().split(",");
                    if (data[0] != null) {
                        records.add(new Service(data[0], data[1], data[2], data[3], data[4], data[5]));
                    }
                }
            }
            else System.out.println("File not found.");
            all_services = new ServiceRecords(records);
        }
    }

    public static synchronized void addService(Service service) {
        if (all_services == null) throw new IllegalStateException("Service not initialized");
        all_services.services.add(service);
    }

    public static synchronized void update() throws IOException {
        if (all_services == null) throw new IllegalStateException("Service not initialized");

        String filePath = STR."\{System.getProperty("user.home")}/Documents/ChocAn";
        new File(filePath).mkdirs();
        File file = new File(filePath + "/ServiceList.txt");
        file.createNewFile();

        java.io.FileWriter myWriter = new java.io.FileWriter(file);
        for (Service service : all_services.services) {
            try { myWriter.write(service.toString() + "\n"); }
            catch (IOException e) { throw new RuntimeException(e); }
        }
        myWriter.close();
    }

    public static synchronized ServiceRecords getInstance() {
        if (all_services == null) throw new IllegalStateException("Service not initialized");
        return all_services;
    }
}
