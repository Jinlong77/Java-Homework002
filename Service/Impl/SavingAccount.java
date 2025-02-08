package Service.Impl;

import Service.Account;

public class SavingAccount implements Account {
    private double balance;

    public SavingAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= 0) {
            balance -= amount;
        }
    }

    @Override
    public void transfer(double amount, Account targetAccount) {

    }

    @Override
    public void displayAccountInfo() {

    }
}
