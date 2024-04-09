package Terminals;

import java.util.Scanner;

public interface Terminal {
    void startTerminal();
    static void terminal(String[] options, Runnable[] actions){
        Scanner scanner = new Scanner(System.in);
        loop: while(true){
            for (int i = 0; i < options.length; i++) System.out.println(STR."\{i}: \{options[i]}");
            System.out.println(STR."\{options.length}: Exit");
            int option;
            try { option = Integer.parseInt(scanner.nextLine()); }
            catch (NumberFormatException e) { System.out.println("Invalid option. Please try again."); continue; }
            if (option == options.length) break;
            for(int i = 0; i < options.length; i++){
                if(option == i){ actions[i].run(); continue loop; }
            }
            System.out.println("Invalid option. Please try again.");
        }
        System.out.println("Exiting terminal");
    }
}
