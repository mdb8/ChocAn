package Records;

import Users.Manager;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerRecords {
    private static ManagerRecords all_managers = null;
    public ArrayList<Manager> managers;
    private ManagerRecords(ArrayList<Manager> p) { this.managers = p; }

    // Static method to create instance of Singleton class
    public static synchronized void initializeManagers() {
        if (all_managers == null) {
            ArrayList<Manager> records = new ArrayList<>();
            InputStream inputStream = ManagerRecords.class.getResourceAsStream("/DataFiles/ManagerList.txt");
            if (inputStream != null) {
                Scanner fileScanner = new Scanner(inputStream);
                while (fileScanner.hasNextLine()) {
                    String[] data = fileScanner.nextLine().split(",");
                    if (data[0] != null) {
                        records.add(new Manager(data[0], data[1]));
                    }
                }
            }
            else System.out.println("File not found.");
            all_managers = new ManagerRecords(records);
        }
    }

    public static synchronized ManagerRecords getInstance()
    {
        if (all_managers == null) throw new IllegalStateException("Member not initialized");
        return all_managers;
    }
}