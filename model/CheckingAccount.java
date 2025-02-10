package model;

import service.Account;

import static config.Message.*;
import static enumeration.AccountType.CHECKING_ACCOUNT;

public class CheckingAccount extends BaseAccount {

    public CheckingAccount(User user) {
        super(user, CHECKING_ACCOUNT);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > balance) {
            printError("Insufficient funds! You can't withdraw more than your balance: " + getBalanceString());
            return false;
        }
        balance -= amount;
        return true;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void transfer(double amount, Account targetAccount) {
        if (amount > balance) {
            printError("Insufficient funds!");
            return;
        }
        balance -= amount;
        targetAccount.deposit(amount);
    }
}