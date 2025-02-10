package event;

import enumeration.AccountType;
import model.CheckingAccount;
import model.SavingAccount;

import static enumeration.EventType.DEPOSIT_MONEY;

public class DepositMoneyEvent extends BaseEvent {
    private final AccountType accountType;
    private final double balance;
    private CheckingAccount checkingAccount;
    private SavingAccount savingAccount;

    public DepositMoneyEvent(AccountType accountType, double balance) {
        super(DEPOSIT_MONEY);
        this.accountType = accountType;
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public String getBalanceString() {
        return String.valueOf(balance);
    }

    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }
}
