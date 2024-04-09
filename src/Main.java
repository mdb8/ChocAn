import Records.*;
import Terminals.*;
import MainAccountingProcedure.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        //All files will be stored in a folder called "ChocAn" in the user's Documents directory

        new File(STR."\{System.getProperty("user.home")}/Documents/ChocAn").mkdirs();

        ManagerRecords.initializeManagers();
        MemberRecords.initializeMembers();
        OperatorRecords.initializeOperators();
        ProviderRecords.initializeProviders();
        ServiceRecords.initializeServices();
        SummaryRecords.initializeSummaries();

        System.out.println("Welcome to the ChocAn system!");
        System.out.println("Enter your user type:");
        String[] options = {"Login as operator", "Login as provider", "Login as manager", "Run main accounting procedure"};
        Runnable[] actions = {new OperatorTerminal()::startTerminal, new ProviderTerminal()::startTerminal, new ManagerTerminal()::startTerminal, MainAccountingProcedure::runMAP};
        Terminal.terminal(options, actions);
    }
}
