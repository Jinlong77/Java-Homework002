package model;

import service.Account;

import static config.Message.printError;
import static enumeration.AccountType.SAVING_ACCOUNT;

public class SavingAccount extends BaseAccount {
    private static final double INTEREST_RATE = 0.05;

    public SavingAccount(User user) {
        super(user, SAVING_ACCOUNT);
    }

    @Override
    public void deposit(double amount) {
        if (amount >= 200) {
            balance += amount + amount * INTEREST_RATE;
        } else {
            balance += amount;
        }
    }

    @Override
    public boolean withdraw(double amount) {
        double twentyPercent = balance * 0.2;
        double maxWithdrawal = balance - twentyPercent;

        if (amount <= maxWithdrawal) {
            balance -= amount;
            return true;
        } else {
            printError("Cannot withdraw $" + String.format("%.2f", amount) +
                    ". You can't withdraw more than 80% of your balance: $" +
                    getBalanceString() + ". At least $" +
                    String.format("%.2f", twentyPercent) + " must remain in your account.");
            return false;
        }
    }

    @Override
    public void transfer(double amount, Account targetAccount) {
        double twentyPercent = balance * 0.2;
        if (balance - amount <= twentyPercent) {
            balance -= amount;
            targetAccount.deposit(amount);
        } else {
            printError("Cannot transfer " + amount + " You can't withdraw more than 80% of your balance: " + getBalanceString() + "At least " + twentyPercent + " must remain in your account.");
        }
    }
}
