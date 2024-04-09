package Records;

import Users.Operator;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class OperatorRecords {
    private static OperatorRecords all_operators = null;
    public ArrayList<Operator> operators;
    private OperatorRecords(ArrayList<Operator> p) { this.operators = p; }

    // Static method to create instance of Singleton class
    public static synchronized void initializeOperators() {
        if (all_operators == null) {
            ArrayList<Operator> records = new ArrayList<>();
            InputStream inputStream = ManagerRecords.class.getResourceAsStream("/DataFiles/OperatorList.txt");
            if (inputStream != null) {
                Scanner fileScanner = new Scanner(inputStream);
                while (fileScanner.hasNextLine()) {
                    String[] data = fileScanner.nextLine().split(",");
                    if (data[0] != null) {
                        records.add(new Operator(data[0], data[1]));
                    }
                }
            }
            else System.out.println("File not found.");
            all_operators = new OperatorRecords(records);
        }
    }

    public static synchronized OperatorRecords getInstance() {
        if (all_operators == null) throw new IllegalStateException("Member not initialized");
        return all_operators;
    }
}