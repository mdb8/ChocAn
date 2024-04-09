package OperatorActions;

import Records.ProviderRecords;
import Users.Provider;
import java.util.*;

public class ManageProviders {
    public static void addProvider(){
        ProviderRecords.addProvider(getProviderData());
        ProviderRecords.update();
    }
    public static void deleteProvider(){
        ProviderRecords.getInstance().providers.remove(getProvider());
        ProviderRecords.update();
    }
    public static void manageProvider(){
        Provider provider = getProvider();
        System.out.println("Enter the field you would like to change: ");
        System.out.println("1. Username");
        System.out.println("2. Password");
        System.out.println("3. ID");
        System.out.println("4. Name");
        System.out.println("5. Address");
        System.out.println("6. City");
        System.out.println("7. State");
        System.out.println("8. Zip Code");
        System.out.println("9. Email");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter the new Username: ");
                provider.Username = scanner.nextLine();
                break;
            case "2":
                System.out.println("Enter the new Password: ");
                provider.Password = scanner.nextLine();
                break;
            case "3":
                System.out.println("Enter the new ID: ");
                provider.ID = scanner.nextLine();
                break;
            case "4":
                System.out.println("Enter the new name: ");
                provider.Name = scanner.nextLine();
                break;
            case "5":
                System.out.println("Enter the new address: ");
                provider.Address = scanner.nextLine();
                break;
            case "6":
                System.out.println("Enter the new city: ");
                provider.City = scanner.nextLine();
                break;
            case "7":
                System.out.println("Enter the new state: ");
                provider.State = scanner.nextLine();
                break;
            case "8":
                System.out.println("Enter the new zip code: ");
                provider.Zip = scanner.nextLine();
                break;
            case "9":
                System.out.println("Enter the new email: ");
                provider.Email = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice.");
        }
        ProviderRecords.update();
    }


    public static Provider getProviderData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the provider's username: ");
        String username = scanner.nextLine();
        System.out.println("Enter the provider's password: ");
        String password = scanner.nextLine();
        System.out.println("Enter the provider's ID: ");
        String id = scanner.nextLine();
        System.out.println("Enter the provider's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the provider's address: ");
        String address = scanner.nextLine();
        System.out.println("Enter the provider's city: ");
        String city = scanner.nextLine();
        System.out.println("Enter the provider's state: ");
        String state = scanner.nextLine();
        System.out.println("Enter the provider's zip code: ");
        String zip = scanner.nextLine();
        System.out.println("Enter the provider's email: ");
        String email = scanner.nextLine();

        return new Provider(username, password, id, name, address, city, state, zip, email);
    }

    public static Provider getProvider() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the provider's ID: ");
            String IDToFind = scanner.nextLine();
            for (Provider provider : ProviderRecords.getInstance().providers) {
                if (provider.ID.equals(IDToFind)) {
                    return provider;
                }
            }
            System.out.println("Provider not found. Try again.");
        }
    }
}
