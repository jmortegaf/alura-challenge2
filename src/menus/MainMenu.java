package menus;

import java.util.Scanner;

public class MainMenu {


    public String show_menu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================================");
        System.out.println("Welcome to Alura Currency Exchange.");
        System.out.println("Select an option.");
        System.out.println("==========================================================");
        System.out.println("[B]ookmarks");
        System.out.println("[E]xchange currency.");
        System.out.println("[H]istory.");
        System.out.println("[Q]uit program.");

        System.out.print(":>");
        return scanner.nextLine();
    }

}
