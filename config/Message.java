package config;

import static config.Color.*;

public class Message {
    public static final String ENTER_USERNAME = "Enter username: ";
    public static final String ENTER_GENDER = "Enter gender: ";
    public static final String ENTER_PHONE = "Enter phone number: ";
    public static final String ENTER_BIRTHDATE = "Enter birth of date (dd-mm-yyyy): ";
    public static final String ENTER_CHOICE_ACCOUNT = "=> Choose an option: ";
    public static final String ENTER_OPTION = "=> Choose option(1-7): ";
    public static final String ENTER_CREATE_ACCOUNT = "What type of account do you want to create?: ";
    public static final String ENTER_MONEY_DEPOSIT = "=> Enter amount of money to deposit: ";
    public static final String ENTER_MONEY_WITHDRAW = "=> Enter amount of money to withdraw: ";
    public static final String ENTER_MONEY_TRANSFER = "=> Enter amount of money to transfer: ";
    public static final String CONFIRM_CONTINUE = "Please input y to continue and n to back: ";
    public static final String CONFIRM_DELETE = "Are you sure you want to delete this account? (y/n): ";

    public static void printMenu() {
        System.out.printf("\n======================= %s Online Banking System %s ========================\n", CYAN , RESET);
        System.out.println(LIGHT_GREEN + "1. Create Account");
        System.out.println(LIGHT_BLUE + "2. Deposit Money");
        System.out.println(YELLOW + "3. Withdraw Money");
        System.out.println(PINK + "4. Transfer Money");
        System.out.println(GREEN + "5. Display Account Information");
        System.out.println(WHITE + "6. Delete Account");
        System.out.println(RED + "7. Exit" + RESET);
        System.out.println("========================================================================\n\n");
        System.out.print(ENTER_OPTION);
    }

    public static void printAccountInfo(String accountType, String accountNumber, String username,String birth, String gender, String phoneNumber, String balance) {
        System.out.printf("\n>>>>>>>>>>>>>>>>>>>>>> %s <<<<<<<<<<<<<<<<<<<<<<<<<<\n", accountType);
        System.out.println("Account Type: " + LIGHT_GREEN + accountType + RESET);
        System.out.println("Account Number: " + LIGHT_BLUE + accountNumber + RESET);
        System.out.println("Username: " + LIGHT_YELLOW + username + RESET);
        System.out.println("Date of Birth: " + LIGHT_CYAN + birth + RESET);
        System.out.println("Gender: " + LIGHT_PURPLE + gender + RESET);
        System.out.println("Phone Number: " + WHITE + phoneNumber + RESET);
        System.out.println("Balance: " + RED + balance + RESET);
        System.out.println("=====================================================================\n");
    }

    public static void printSuccessDeposit(String accountType, String amount, String totalAmount) {
        System.out.println("\n\t\t\t\t\t\t\t⬇️");
        System.out.println("\t\t\t\t\t\t" + accountType);
        System.out.println("Received: \t\t\t\t\t\t\t\t\t $" + LIGHT_GREEN + amount + RESET);
        System.out.println("Total Amount: \t\t\t\t\t\t\t\t $" + RED + totalAmount + RESET);
        System.out.println("==========================================================\n");
        System.out.println("✅ Deposit successfully!");
    }

    public  static void printSuccessWithdraw(String accountType, String amount, String totalAmount){
        System.out.println("\n\t\t\t\t\t\t\t⬆️");
        System.out.println("\t\t\t\t\t\t" + accountType);
        System.out.println("Withdraw: \t\t\t\t\t\t\t\t\t $" + RED + amount + RESET);
        System.out.println("Total Amount: \t\t\t\t\t\t\t\t $" + LIGHT_CYAN + totalAmount + RESET);
        System.out.println("==========================================================\n");
        System.out.println("✅ Withdraw successfully!");
    }

    public static void printTransferMenu(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>   Transfer Money   <<<<<<<<<<<<<<<<<<<<");
        System.out.println("1. Checking Account -> Saving Account");
        System.out.println("2. Saving Account   -> Checking Account");
        System.out.println("3. Back");
        System.out.println("=============================================================");
        System.out.print("Choose an option: ");
    }

    public static void printTransferSuccess(String fromAccountType, String toAccountType, double transferAmount ,String saveAccountNumber, String checkingAccountNumber, double checkingAccountBalance, double saveAccountBalance) {
        System.out.print(">>>>>>>>>>>>>>>>>>>>   " + fromAccountType + "   <<<<<<<<<<<<<<<<<<<<\n");
        System.out.printf("Transferred :                              $ %.2f\n", transferAmount);
        System.out.printf("From        : " + fromAccountType + " with ID: %s\n", checkingAccountNumber);
        System.out.printf("To          :   " + toAccountType + " with ID: %s\n", saveAccountNumber);
        System.out.printf("Total Remain:                              $ %.2f\n", checkingAccountBalance);
        System.out.println("\n                       ⬇️                        \n");
        System.out.print("                 " + toAccountType + "                        \n");
        System.out.printf("Received    :                              $ %.2f\n", transferAmount);
        System.out.printf("Total Amount:                              $ %.2f\n", saveAccountBalance);
    }

    public static void chooseAccountType(String title) {
        System.out.printf("\n======================= %s ========================\n", title);
        System.out.println(LIGHT_GREEN + "1. Checking Account");
        System.out.println(LIGHT_BLUE + "2. Saving Account");
        System.out.println(WHITE + "3. Back" + RESET);
        System.out.println("==============================================================\n");
    }

    public static void printError(String message){
        System.out.println("\n" + RED + "❌ " + message + RESET);
    }

    public static void printSuccess(String message){
        System.out.println("\n" + GREEN + "✅ " + message + RESET);
    }
}
