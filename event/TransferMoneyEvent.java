package event;

import enumeration.AccountType;

import static enumeration.EventType.TRANSFER_MONEY;

public class TransferMoneyEvent extends BaseEvent {
    private final AccountType fromAccountType;
    private final AccountType toAccountType;
    private final double balance;

    public TransferMoneyEvent(AccountType toAccountType, AccountType fromAccountType, double balance) {
        super(TRANSFER_MONEY);
        this.toAccountType = toAccountType;
        this.fromAccountType = fromAccountType;
        this.balance = balance;
    }

    public AccountType getToAccountType() {
        return toAccountType;
    }

    public AccountType getFromAccountType() {
        return fromAccountType;
    }

    public double getBalance() {
        return balance;
    }
}
