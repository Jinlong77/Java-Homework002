package Config;

import static Config.Color.*;

public class Message {
    public static final String ENTER_USERNAME = "Enter username: ";
    public static final String ENTER_GENDER = "Enter gender: ";
    public static final String ENTER_PHONE = "Enter phone number: ";
    public static final String ENTER_BIRTHDATE = "Enter birth of date (dd-mm-yyyy): ";
    public static final String ENTER_CHOICE = "=> Choose option(1-7) : ";
    public static final String ENTER_OPTION = "=> Enter an option: ";
    public static final String ENTER_CREATE_ACCOUNT = "What type of account do you want to create?: ";


//    public static final String BOOK_ID = "Enter Book ID: ";
//    public static final String ENTER_BOOK_TITLE = "Enter Book Title: ";
//    public static final String ENTER_AUTHOR = "Enter Author Name: ";
//    public static final String ENTER_AUTHOR_YEAR = "Enter Author Year Active: ";
//    public static final String ENTER_PUBLISH_YEAR = "Enter Book Publish Year: ";
//    public static final String ENTER_ROW = "Enter row to show record: ";
//    public static final String ENTER_ID = "Enter Book ID to delete: ";
//    public static final String ENTER_BORROW_ID = "Enter Book ID to borrow: ";
//    public static final String ENTER_RETURN_ID = "Enter Book ID to return: ";
//    public static final String CONFIRM_CONTINUE_BORROW = "Do you want to continue borrowing? [y/N]: ";
//    public static final String CONFIRM_CONTINUE_RETURN = "Do you want to continue returning? [y/N]: ";
//    public static final String TABLE_FOOTER = "\n" + BLUE +  "1. Next Page \t" + PURPLE +" 2. Previous Page \t" + CYAN +" 3. First Page \t" + GREEN +" 4. Last Page \t" + RED + " 5. Exit" + RESET;

//    public static void printBorrowAndReturnSuccess(String id, String title, String author, String publishYear, boolean isBorrow){
//        System.out.println("\nBook ID: " + LIGHT_BLUE + id + RESET);
//        System.out.println("Book Title: " + LIGHT_PURPLE + title + RESET);
//        System.out.println("Book Author: " + LIGHT_CYAN + author + RESET);
//        System.out.println("Book Publish Year: " + LIGHT_GREEN + publishYear + RESET + "\n");
//        System.out.println("===============================================");
//        System.out.println("||\t" + LIGHT_GREEN + "📚 Book with id " + LIGHT_CYAN + id + RESET + LIGHT_GREEN + (isBorrow ? " has been borrowed\t" : " has been returned") + RESET + " ||");
//        System.out.println("===============================================");
//    }

    public static void printMenu() {
        System.out.printf("\n======================= %s Online Banking System %s ========================\n", CYAN , RESET);
        System.out.println(LIGHT_GREEN + "1. Create Account");
        System.out.println(LIGHT_BLUE + "2. Deposit Money");
        System.out.println(YELLOW + "3. Withdraw Money");
        System.out.println(PINK + "4. Transfer Money");
        System.out.println(GREEN + "5. Display Account Information");
        System.out.println(WHITE + "6. Delete Account");
        System.out.println(RED + "7. Exit" + RESET);
        System.out.println("============================================================\n\n");
        System.out.print(ENTER_OPTION);
    }

    public static void printError(String message){
        System.out.println("\n" + RED + "❌ " + message + RESET);
    }

    public static void printSuccess(String message){
        System.out.println("\n" + GREEN + "✅ " + message + RESET);
    }
}
