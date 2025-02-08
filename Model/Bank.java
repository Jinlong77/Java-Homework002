package Model;

import Service.Impl.CheckingAccount;
import Service.Impl.SavingAccount;

import java.util.Scanner;

import static Config.Message.printError;
import static Config.Message.printMenu;

public class Bank {
    private final Scanner scanner;
    private final User user;
    private CheckingAccount checkingAccount;
    private SavingAccount savingAccount;

    public Bank() {
        user = new User();
        scanner = new Scanner(System.in);
    }

    public void init() {
        menu();
        scanner.close();
    }

    private void menu( ) {
        printMenu();
        do {
            printError("");
        } while (user.setUsername(scanner.nextLine()));
    }

    private
}
