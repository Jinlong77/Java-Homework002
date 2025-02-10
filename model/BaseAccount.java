package model;

import enumeration.AccountType;
import service.Account;

import java.util.Random;

import static config.Message.printAccountInfo;

public abstract class BaseAccount implements Account {
    protected User user;
    protected double balance;
    protected long accountNumber;
    protected AccountType accountType;

    public BaseAccount(User user, AccountType accountType) {
        this.user = user;
        this.balance = 0;
        this.accountNumber = new Random().nextLong(100000000, 999999999);
        this.accountType = accountType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBalanceString() {
        return String.format("%.2f", balance);
    }

    public String getAccountNumberString() {
        return String.valueOf(accountNumber);
    }

    @Override
    public void displayAccountInfo() {
        printAccountInfo(
                accountType.getValue(),
                getAccountNumberString(),
                user.getUsername(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getPhoneNumber(),
                getBalanceString()
        );
    }
}
