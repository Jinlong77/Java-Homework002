package service.impl;

import enumeration.AccountType;
import event.*;
import event.listener.EventListener;
import event.impl.EventListenerImpl;
import event.publisher.EventPublisher;
import model.BaseAccount;
import model.CheckingAccount;
import model.SavingAccount;
import model.User;
import service.BankService;

import java.util.Objects;
import java.util.Scanner;

import static config.Config.REGEX_MONEY;
import static config.Config.REGEX_YN;
import static config.Message.*;
import static config.Validation.isChoiceValid;
import static config.Validation.isOptionValid;
import static enumeration.AccountType.CHECKING_ACCOUNT;
import static enumeration.AccountType.SAVING_ACCOUNT;

public class BankSystem implements BankService {
    private final Scanner scanner;
    private final EventPublisher eventPublisher;
    private BaseAccount saveAccount;
    private BaseAccount checkingAccount;
    public BankSystem() {
        scanner = new Scanner(System.in);
        eventPublisher = new EventPublisher();
    }

    public void start() {
        menu();
        scanner.close();
    }

    private void menu() {
        do {
            printMenu();
            String option = isOptionValid(scanner.nextLine());
            if (Objects.equals(option, "7")) {
                System.out.println("Goodbye!");
                System.out.println("Thank you for using our service.");
                System.exit(0);
            }
            if (option != null) {
                switch (option) {
                    case "1" -> createAccount();
                    case "2" -> deposit();
                    case "3" -> withdraw();
                    case "4" -> transfer();
                    case "5" -> displayAccountInfo();
                    case "6" -> deleteAccount();
                }
            }
        } while (true);
    }

    public void createAccount() {
        EventListener eventListener = new EventListenerImpl();
        CreateAccountEvent createAccountEvent;
        chooseAccountType("Create Account");
        System.out.print(ENTER_CREATE_ACCOUNT);
        String option = isChoiceValid(scanner.nextLine());
        if (option == null) {
            createAccount();
            return;
        }
        System.out.println("=================== Account Information ==================");
        if (option.equals("1") && checkingAccount != null) {
            printError("Checking account already exists!");
            createAccount();
            return;
        }
        if (option.equals("2") && saveAccount != null) {
            printError("Saving account already exists!");
            createAccount();
            return;
        }
        if (option.equals("3")) {
            menu();
            return;
        }
        User user = inputUserInformation();
        if (option.equals("1")) {
            checkingAccount = new CheckingAccount(user);
            createAccountEvent = new CreateAccountEvent(CHECKING_ACCOUNT, user);
        } else {
            saveAccount = new SavingAccount(user);
            createAccountEvent = new CreateAccountEvent(SAVING_ACCOUNT, user);
        }
        eventPublisher.addListener(eventListener);
        eventPublisher.notifyListeners(createAccountEvent);
    }

    public void deposit() {
        if (checkingAccount == null && saveAccount == null) {
            printError("No account exists!");
            return;
        }
        chooseAccountType("Deposit Money");
        System.out.print(ENTER_CHOICE_ACCOUNT);
        String accountTypeOption = scanner.nextLine();
        if(accountTypeOption.equals("3")) {
            menu();
            return;
        }
        AccountType accountType = accountTypeOption.equals("1") ? CHECKING_ACCOUNT : SAVING_ACCOUNT;
        if (accountType.equals(CHECKING_ACCOUNT) && checkingAccount == null) {
            printError("Checking account does not exist!");
            deposit();
            return;
        }

        if (accountType.equals(SAVING_ACCOUNT) && saveAccount == null) {
            printError("Saving account does not exist!");
            deposit();
            return;
        }

        String amount;
        do {
            System.out.print(ENTER_MONEY_DEPOSIT);
            amount = scanner.nextLine();
        } while (isAmountValid(amount));

        switch (accountType) {
            case CHECKING_ACCOUNT -> {
                DepositMoneyEvent depositEvent = new DepositMoneyEvent(CHECKING_ACCOUNT, Double.parseDouble(amount));
                depositEvent.setCheckingAccount((CheckingAccount) checkingAccount);
                eventPublisher.notifyListeners(depositEvent);
            }
            case SAVING_ACCOUNT -> {
                DepositMoneyEvent depositEvent = new DepositMoneyEvent(SAVING_ACCOUNT, Double.parseDouble(amount));
                depositEvent.setSavingAccount((SavingAccount) saveAccount);
                eventPublisher.notifyListeners(depositEvent);
            }
        }
    }

    public void withdraw() {
        if (checkingAccount == null && saveAccount == null) {
            printError("No account exists!");
            return;
        }

        chooseAccountType("Withdraw Money");
        System.out.print(ENTER_CHOICE_ACCOUNT);
        String accountTypeOption = scanner.nextLine();
        AccountType accountType = accountTypeOption.equals("1") ? CHECKING_ACCOUNT : SAVING_ACCOUNT;

        if (accountTypeOption.equals("3")) {
            menu();
            return;
        }

        if (accountType.equals(CHECKING_ACCOUNT) && checkingAccount == null) {
            printError("Checking account does not exist!");
            withdraw();
            return;
        }

        if (accountType.equals(SAVING_ACCOUNT) && saveAccount == null) {
            printError("Saving account does not exist!");
            withdraw();
            return;
        }

        String amount;
        double withdrawAmount;
        do {
            System.out.print(ENTER_MONEY_WITHDRAW);
            amount = scanner.nextLine();
            withdrawAmount = Double.parseDouble(amount);

            if (accountType.equals(SAVING_ACCOUNT)) {
                if (withdrawAmount > saveAccount.getBalance() * 0.8) {
                    printError("Cannot withdraw more than 80% of your savings account balance.");
                    isContinueWithdraw(accountType);
                    return;
                }
            }

            if (accountType.equals(CHECKING_ACCOUNT)) {
                if (!checkingAccount.withdraw(withdrawAmount)) {
                    isContinueWithdraw(accountType);
                    return;
                }
            }
            if (accountType.equals(SAVING_ACCOUNT)) {
                saveAccount.withdraw(withdrawAmount);
                WithdrawMoneyEvent withdrawEvent = new WithdrawMoneyEvent(SAVING_ACCOUNT, withdrawAmount, saveAccount.getBalance());
                eventPublisher.notifyListeners(withdrawEvent);
                return;
            } else {
                checkingAccount.withdraw(withdrawAmount);
                WithdrawMoneyEvent withdrawEvent = new WithdrawMoneyEvent(CHECKING_ACCOUNT, withdrawAmount, checkingAccount.getBalance());
                eventPublisher.notifyListeners(withdrawEvent);
                return;
            }
        } while (!isAmountValid(amount));




    }

    private void isContinueWithdraw(AccountType accountType) {
        while (true) {
            System.out.print(CONFIRM_CONTINUE);
            String shortAnswer = scanner.nextLine().trim().toLowerCase();

            if (shortAnswer.equals("n")) {
                return;
            } else if (shortAnswer.equals("y")) {
                withdraw();
                break;
            } else {
                printError("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    public void transfer() {
        if (checkingAccount == null && saveAccount == null) {
            printError("No account exists!");
            return;
        }

        if (checkingAccount == null || saveAccount == null) {
            printError("Only one account exists!");
            return;
        }

        printTransferMenu();
        String accountTypeOption = scanner.nextLine();

        if (accountTypeOption.equals("3")) {
            menu();
            return;
        }

        BaseAccount fromAccount, toAccount;
        if (accountTypeOption.equals("1")) {
            if (checkingAccount == null) {
                printError("Checking account does not exist!");
                return;
            }
            if (saveAccount == null) {
                printError("Saving account does not exist!");
                return;
            }
            fromAccount = checkingAccount;
            toAccount = saveAccount;
        } else if (accountTypeOption.equals("2")) {
            if (saveAccount == null) {
                printError("Saving account does not exist!");
                return;
            }
            if (checkingAccount == null) {
                printError("Checking account does not exist!");
                return;
            }
            fromAccount = saveAccount;
            toAccount = checkingAccount;
        } else {
            printError("Invalid choice! Please enter 1 or 2.");
            return;
        }

        double amount;
        do {
            System.out.print("Enter money to withdraw: ");
            String amountInput = scanner.nextLine();
            amount = parseAmount(amountInput);
            if (amount > fromAccount.getBalance()) {
                printError("Amount exceeds balance. Please enter a valid amount.");
                System.out.println("Do you want to continue? (y/n): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("n")) {
                    return;
                }
            }
        } while (amount <= 0 || amount > fromAccount.getBalance());

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        printTransferSuccess(
                fromAccount.getAccountType().getValue(),
                toAccount.getAccountType().getValue(), amount,
                fromAccount.getAccountNumberString(),
                toAccount.getAccountNumberString(),
                fromAccount.getBalance(),
                toAccount.getBalance()
        );
        TransferMoneyEvent transferEvent = new TransferMoneyEvent(fromAccount.getAccountType(), toAccount.getAccountType(), amount);
        eventPublisher.notifyListeners(transferEvent);
    }

    private double parseAmount(String input) {
        if (input.matches(REGEX_MONEY)) {
            return Double.parseDouble(input);
        } else {
            printError("Invalid amount! Please enter a valid number.");
            return -1;
        }
    }

    public void displayAccountInfo() {
        if (checkingAccount == null && saveAccount == null) {
            printError("No account exists!");
            return;
        }
        System.out.println("=================== Account Information ==================");
        if (checkingAccount != null) {
            checkingAccount.displayAccountInfo();
        }
        if (saveAccount != null) {
            saveAccount.displayAccountInfo();
        }
        eventPublisher.notifyListeners(new DisplayAccountEvent());
    }

    public void deleteAccount() {
        if (checkingAccount == null && saveAccount == null) {
            printError("No account exists!");
            return;
        }
        if (checkingAccount == null || saveAccount == null) {
            printError("Only one account exists!");
            return;
        }
        chooseAccountType("Delete Account");
        System.out.print(ENTER_CHOICE_ACCOUNT);
        String accountTypeOption = scanner.nextLine();

        if (accountTypeOption.equals("3")) {
            menu();
            return;
        }

        AccountType accountType = accountTypeOption.equals("1") ? CHECKING_ACCOUNT : SAVING_ACCOUNT;

        System.out.println("=================== Account Information ==================");
        switch (accountType) {
            case CHECKING_ACCOUNT -> checkingAccount.displayAccountInfo();
            case SAVING_ACCOUNT -> saveAccount.displayAccountInfo();
        }

        String choice;
        do {
            System.out.println(CONFIRM_DELETE);
            choice = isContinue(scanner.nextLine());
        } while (choice == null);

        if (choice.equalsIgnoreCase("n")) {
            return;
        }

        double transferAmount;
        if (accountType == CHECKING_ACCOUNT) {
            transferAmount = checkingAccount.getBalance();
            saveAccount.deposit(transferAmount);
            checkingAccount.withdraw(transferAmount);
            printTransferSuccess("Checking Account", "Saving Account", transferAmount, saveAccount.getAccountNumberString(), checkingAccount.getAccountNumberString(), saveAccount.getBalance(), checkingAccount.getBalance());
            checkingAccount = null;
        } else {
            transferAmount = saveAccount.getBalance();
            checkingAccount.deposit(transferAmount);
            saveAccount.withdraw(transferAmount);
            printTransferSuccess("Saving Account", "Checking Account", transferAmount, saveAccount.getAccountNumberString(), checkingAccount.getAccountNumberString(), checkingAccount.getBalance(), saveAccount.getBalance());
            saveAccount = null;
        }

        System.out.println("==============================================================");
        System.out.println("Transferred all balance from " + (accountType == CHECKING_ACCOUNT ? "Checking" : "Saving") + " account to " + (accountType == CHECKING_ACCOUNT ? "Savings" : "Checking") + " account.");
        eventPublisher.notifyListeners(new DeleteAccountEvent(accountType));
    }

    private String isContinue(String shortAnswer) {
        if (shortAnswer.matches(REGEX_YN)) {
            return shortAnswer;
        } else {
            printError("Invalid input. Please enter Y or n.");
            return null;
        }
    }

    private User inputUserInformation() {
        User user = new User();
        for (int i = 0; i < 4; i++) {
            do {
                System.out.print(i == 0 ? ENTER_USERNAME : i == 1 ? ENTER_BIRTHDATE : i == 2 ? ENTER_GENDER : ENTER_PHONE);
            } while (i == 0 ? !user.setUsername(scanner.nextLine()) : i == 1 ? !user.setDateOfBirth(scanner.nextLine()) : i == 2 ? !user.setGender(scanner.nextLine()) : !user.setPhone(scanner.nextLine()));
        }
        return user;
    }

    private boolean isAmountValid(String amount) {
        if (amount.matches(REGEX_MONEY)) {
            return false;
        } else {
            printError("Invalid amount. Please enter a valid amount. (e.g. 1000 or 1000.00)");
            return true;
        }
    }
}